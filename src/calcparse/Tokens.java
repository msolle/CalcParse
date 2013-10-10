package calcparse;

public interface Tokens {

		// A set of constants to use as tokens in the parser
		// and scanner.
	
		public static final int READ = 200;
		public static final int WRITE = 201;
		public static final int ID = 202;
		public static final int NUMBER = 203;
		public static final int EOP = 204;
		public static final int EOF = 205;
		public static final int MULOP = 206;
		public static final int ADDOP = 207;
		public static final int ERROR = 208;
		public static final int ASSIGNOP = 209;
                public static final int LEFTPAREN = 210;
                public static final int RIGHTPAREN = 211;
}