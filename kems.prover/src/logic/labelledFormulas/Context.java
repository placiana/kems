package logic.labelledFormulas;

import java.util.Objects;

public class Context {

	private int ordinal;

	public Context(int i) {
		ordinal = i;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ordinal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Context other = (Context) obj;
		return ordinal == other.ordinal;
	}

}
