import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BlockChain {

	private static class Node {
		public Block value;
		public Node next;

		public Node(Block value, Node next) {
			this.value = value;
			this.next = next;
		}
	}

	private Node first;
	private Node last;

	BlockChain(int initial) throws NoSuchAlgorithmException {
		Hash none = new Hash(new byte[0]);

		if (initial < 0) {
			throw new IllegalArgumentException();
		}

		Node n = new Node(new Block(0, initial, none), null);
		this.first = n;
		this.last = null;
	}

	public Block mine(int amount) throws NoSuchAlgorithmException {

		int truthValue = -1;
		long newNonceValue = 0;
		int num = this.getSize() + 1;
		Block newBlock = new Block(num, amount, this.last.value.getHash());

		while (truthValue != 0) {

			MessageDigest md = MessageDigest.getInstance("sha-256");
			byte[] byteNum = ByteBuffer.allocate(4).putInt(num).array();
			byte[] byteAmt = ByteBuffer.allocate(4).putInt(amount).array();
			byte[] bytePrevHash = newBlock.getPrevHash().toString().getBytes();
			byte[] byteNonce = ByteBuffer.allocate(4).putLong(newNonceValue).array();

			md.update(byteNum);
			md.update(byteAmt);

			if (num != 0) {
				md.update(bytePrevHash);
			}

			md.update(byteNonce);
			byte[] blockHash = md.digest();
			Hash newHashValue = new Hash(blockHash);

			if (newHashValue.isValid()) {
				newBlock.setHash(newHashValue);
				truthValue = 0;
			} else {
				newNonceValue++;
			}

		}
		newBlock.setNonce(newNonceValue);
		return newBlock;
	}

	public int getSize() {
		return last.value.getNum();
	}

	public void append(Block blk) throws IllegalArgumentException {
		blk.setPrevHash(this.last.next.value.getHash());
		this.last.next.value = blk;
	}

	public boolean removeLast() {
		Node cur = this.first;
		if (this.last == null) {
			return false;
		}
		while (cur.next != this.last) {
			cur = cur.next;
		}
		this.last = cur;
		return true;
	}

	public Hash getHash() {
		return last.value.getHash();
	}

	public boolean isValidBlockChain() {
		Node cur = this.first;
		while (cur != null) {
			if (cur.value.getHash().isValid() && (cur.value.getHash() == cur.next.value.getPrevHash())
					&& (cur.value.getAmount() >= 0)) {
				cur = cur.next;
			} else {
				return false;
			}
		}
		return true;
	}

	public void printBalances() {
		int amount1 = this.first.value.getAmount();
		int amount2 = 0;
		Node cur = this.first;
		while (cur.next != null) {
			int money = cur.value.getAmount();
			amount1 -= money;
			amount2 += money;
		}
		System.out.println("Alice: " + amount1 + ", Bob: " + amount2);

	}

	public String toString() {
		Node cur = first;
		String ret = "";
		while (cur != null) {
			ret = ret + cur.value.toString()+ '\n';
			cur = cur.next;
		}
		return ret;
	}

}
