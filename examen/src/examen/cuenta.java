package examen;

import java.util.ArrayList;


public class cuenta {
String tipocuenta;
double montoinicial;
ArrayList<movimiento> mismovimientos;
public cuenta(String tipocuenta, double montoinicial) {
	super();
}
public cuenta() {
	mismovimientos=new ArrayList<movimiento>();
}
public void addMovimiento(movimiento m) {
	mismovimientos.add(m);
}
public ArrayList<movimiento> getMismovimientos() {
	return mismovimientos;
}
public void setMismovimientos(ArrayList<movimiento> mismovimientos) {
	this.mismovimientos = mismovimientos;
}
public String getTipocuenta() {
	return tipocuenta;
}
public void setTipocuenta(String tipocuenta) {
	this.tipocuenta = tipocuenta;
}
public double getMontoinicial() {
	return montoinicial;
}
public void setMontoinicial(double montoinicial) {
	this.montoinicial = montoinicial;
}

public void addmovimiento(movimiento m) {

	
}
public movimiento[] getMisMovimientos() {

	return null;
}
}
