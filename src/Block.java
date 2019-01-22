//import java.nio.ByteBuffer;
//import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
	private int num;
	private int amount;
	private Hash prevHash;
	private long nonce;
	private Hash hash;

	public Block(int num, int amt, Hash prevHash)throws NoSuchAlgorithmException {
		//int truthValue = 1;
		this.num = num;
		this.amount = amt;
		this.prevHash = prevHash;
		//nonceFinder(this.num, this.amount);
		/*this.nonce = 0;
		while (truthValue != 0) {
			Hash newHash = new Hash(nonceFinder(this.num, this.amount, this.prevHash, this.nonce));
			if (newHash.isValid()) {
				this.hash = newHash;
				truthValue = 0;
			} else {
				this.nonce++;
			}
		}*/

	}

	public Block(int num, int amount, Hash prevHash, long nonce) {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;

	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public long getNonce() {
		return nonce;
	}

	public void setNonce(long nonce) {
		this.nonce = nonce;
	}
	
	public Hash getPrevHash() {
		return prevHash;
	}
	public void setPrevHash(Hash hashVal) {
		this.prevHash = hashVal;
	}
	public Hash getHash() {
		return hash;
	}
	public void setHash(Hash hashVal) {
		this.hash = hashVal;
	}

	public String toString() {
		return "Block " + num + " (Amount: " + amount + ", Nonce: " + nonce 
				+ ", prevHash: " + prevHash+ ", hash: " + hash + ")";
	}

	/*
	public void nonceFinder(int num, int amount) throws NoSuchAlgorithmException {
		int truthValue = -1;
		long newNonceValue = 0;
		while (truthValue != 0) {

			MessageDigest md = MessageDigest.getInstance("sha-256");
			byte[] byteNum = ByteBuffer.allocate(4).putInt(num).array();
			byte[] byteAmt = ByteBuffer.allocate(4).putInt(amount).array();
			byte[] bytePrevHash = this.prevHash.toString().getBytes();
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
				this.hash = newHashValue;
				truthValue = 0;
			} else {
				newNonceValue++;
			}
			
		}
		this.nonce = newNonceValue;
	}*/

}