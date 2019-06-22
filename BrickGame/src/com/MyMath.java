package com;

public class MyMath {
	public static double interpolation(double from, double to, double target) {
		return from * ( 1 - target ) + to * target;
	}
}
