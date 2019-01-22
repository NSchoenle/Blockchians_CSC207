import java.security.NoSuchAlgorithmException;
import java.util.*;

public class BlockChainDriver {

	public static void printCommands() {
		System.out.printf("Valid commands: \n" 
				+ "\tmine: discovers the nonce for a given transaction\n"
				+ "\tappend: appends a new block onto the end of the chain\n"
				+ "\tremove: removes the last block from the end of the chain\n"
				+ "\tcheck: checks that the block chain is valid\n"
				+ "\treport: reports the balances of Alice and Bob\n" 
				+ "\thelp: prints this list of commands\n"
				+ "\tquit: quits the program\n");

	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner in = new Scanner(System.in);
		String initialAmount = args[0];
		int annaStart = Integer.parseInt(initialAmount);

		BlockChain chain = new BlockChain(annaStart);

		System.out.print(chain.toString());

		System.out.println("Command? ");
		String com = in.nextLine();

		while (com != "quit") {
			System.out.println("Command? ");
			if (com == "mine") {
				System.out.println("Amount transfered? ");
				int amt = in.nextInt();
				Block minedBlock = chain.mine(amt);
				System.out.println("amount = " + amt + ", nonce" + minedBlock.getNonce());
			} else if (com == "append") {
				System.out.println("Amount Transfered? ");
				int amtTransfer = in.nextInt();
				System.out.println("Nonce? ");
				long nonce = in.nextLong();
				Hash none = new Hash(new byte[0]);
				chain.append(new Block(chain.getSize() + 1, amtTransfer, none, nonce));

			} else if (com == "remove") {
				chain.removeLast();
			} else if (com == "check") {
				if (chain.isValidBlockChain()) {
					System.out.println("Chain is valid!");
				}

			} else if (com == "report") {
				chain.printBalances();
			} else if (com == "help") {
				printCommands();
			}
			System.out.print(chain.toString());
			com = in.nextLine();
		}
		in.close();
	}

}
