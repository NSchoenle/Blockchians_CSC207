import java.util.Arrays;

public class Hash {
	private byte[] data;

	public Hash(byte[] dataArr) {
		this.data = dataArr;
	}

	public byte[] getData() {
		return this.data;
	}

	public boolean isValid() {
		for (int i = 0; i < 3; i++) {
			if (data[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < data.length; i++) {
			int byteInt = Byte.toUnsignedInt(data[i]);
			String hexStr = Integer.toHexString(byteInt);
			ret = ret + hexStr;
		}
		return ret;
	}

	public boolean equals(Object other) {
		if (other.getClass() != this.getClass()) {
			return false;
		} else {
			Hash o = (Hash)other; 
			if (Arrays.equals(this.data, o.data)){ 
					return true;
				}else {return false;}
		}
	}
}
