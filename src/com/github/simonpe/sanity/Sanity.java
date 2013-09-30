package com.github.simonpe.sanity;

public class Sanity {
	private final boolean inverse;
	
	private Sanity(boolean inv) {
		inverse = inv;
		if (inv)
			Not = Sane;
		else
			Not = new Sanity(true);
	}
	
	public static final Sanity Sane = new Sanity(false);
	public final Sanity Not;
	
	public static final Compare MAX = Compare.MAX;
	public static final Compare EQUAL = Compare.EQUAL;
	public static final Compare MIN = Compare.MIN;
	public static enum Compare {
		MAX,
		EQUAL,
		MIN
	}
	
	
	public void Null(Object o) {
		if ((o == null) == inverse)
			throw new IllegalArgumentException();
	}
	public void Null(Object o1, Object...o2) {
		Null(o1);
		for(Object o: o2)
			Null(o);
	}
	
	private abstract class _Length<U> {
		protected final int length;
		protected final String type;
		protected U inst;
		
		protected _Length(String type, int length) {
			this.type = type;
			this.length = length;
		}
		
		public U Empty() {
			return Max(0);
		}
		public U Min(int l) {
			if ((length >= l) == inverse)
				throw new IllegalLengthException(type, inverse?"be shorther than":"of minimum length", l, length);
			return inst;
		}
		public U Length(int l) {
			if ((length == l) == inverse)
				throw new IllegalLengthException(type, inverse?"not be of length":"be of length", l, length);
			return inst;
		}
		public U Max(int l) {
			if ((length <= l) == inverse)
				throw new IllegalLengthException(type, inverse?"be longer than":"of maximum length", l, length);
			return inst;
		}
	}
	private class IllegalLengthException extends IllegalArgumentException {
		private static final long serialVersionUID = 1L;

		public IllegalLengthException(String type, String comp, int l, int al) {
			super(java.lang.String.format("The %s should %s %d, receved length %d", type, comp, l, al));
		}
	}
	
	public final _Array Array = new _Array();
	public class _Array {
		
		public _Array Min(int l, char[] a)		{return Length(MIN, l, a.length);}
		public _Array Min(int l, byte[] a)		{return Length(MIN, l, a.length);}
		public _Array Min(int l, short[] a)		{return Length(MIN, l, a.length);}
		public _Array Min(int l, int[] a)		{return Length(MIN, l, a.length);}
		public _Array Min(int l, long[] a)		{return Length(MIN, l, a.length);}
		public _Array Min(int l, float[] a)		{return Length(MIN, l, a.length);}
		public _Array Min(int l, Double[] a)	{return Length(MIN, l, a.length);}
		public <T> _Array Min(int l, T[] a)		{return Length(MIN, l, a.length);}
		
		public _Array Max(int l, char[] a)		{return Length(MAX, l, a.length);}
		public _Array Max(int l, byte[] a)		{return Length(MAX, l, a.length);}
		public _Array Max(int l, short[] a)		{return Length(MAX, l, a.length);}
		public _Array Max(int l, int[] a)		{return Length(MAX, l, a.length);}
		public _Array Max(int l, long[] a)		{return Length(MAX, l, a.length);}
		public _Array Max(int l, float[] a)		{return Length(MAX, l, a.length);}
		public _Array Max(int l, Double[] a)	{return Length(MAX, l, a.length);}
		public <T> _Array Max(int l, T[] a)		{return Length(MAX, l, a.length);}
		
		public _Array Length(int l, char[] a)	{return Length(EQUAL, l, a.length);}
		public _Array Length(int l, byte[] a)	{return Length(EQUAL, l, a.length);}
		public _Array Length(int l, short[] a)	{return Length(EQUAL, l, a.length);}
		public _Array Length(int l, int[] a)	{return Length(EQUAL, l, a.length);}
		public _Array Length(int l, long[] a)	{return Length(EQUAL, l, a.length);}
		public _Array Length(int l, float[] a)	{return Length(EQUAL, l, a.length);}
		public _Array Length(int l, double[] a)	{return Length(EQUAL, l, a.length);}
		public <T> _Array Length(int l, T[] a)	{return Length(EQUAL, l, a.length);}

		private <T> _Array Length(Compare c, int l, int al) {
//			Sanity.this.Length("array", c, l, al);
			return this;
		}
	}
	
	public _String String(CharSequence s) {
		return new _String(s);
	}
	public class _String extends _Length<_String> {
		private final CharSequence s;

		protected _String(CharSequence s) {
			super("string", s.length());
			this.s = s;
			inst = this;
		}
		
	}
/*	public final _String String = new _String();
	public class _String {
		public _String Empty(CharSequence s)			{return Max(0, s);}
		public _String Min(int l, CharSequence s)		{return Length(MIN, l, s);}
		public _String Max(int l, CharSequence s)		{return Length(MAX, l, s);}
		public _String Length(int l, CharSequence s)	{return Length(EQUAL, l, s);}
		
		private _String Length(Compare c, int l, CharSequence s) {
			Sanity.this.Length("string", c, l, s.length());
			return this;
		}
	}*/

}
