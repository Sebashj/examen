package examen;
import java.util.ArrayList;

public class cliente {
String nombre;
String telefono;
String direccion;
ArrayList<cuenta> miscuentas;

public cliente() {
	miscuentas=new ArrayList<cuenta>();
}
public void addCuenta(cuenta c) {
	miscuentas.add(c);
}
public ArrayList<cuenta> getMiscuentas(){
	return miscuentas;
}
public cliente(String nombre, String telefono, String direccion) {
	super();
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}

}