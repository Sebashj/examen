package examen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Banco {

	private JFrame frmExamen;
	private JTextField txtNombre;
	private JTextField txtelefono;
	private JTextField txtDireccion;
	private JButton btnAgregarCliente;
	private JTextField txtmonto;
	private JComboBox cboCliente;
	private JComboBox cboCuenta;
	private JButton btnagredar;
	private JButton btnagregarcuenta;
	private JTextField txtxmonto;
	private JComboBox cboClientes;
	private JComboBox cboTipocuenta;
	private JComboBox cboMovimiento;
	private JButton btnAgregarMovimiento;
	private JLabel lblNombresc;
	private JLabel lblTipoc;
	private JLabel lblDirecc;
	private JLabel lbltelc;
	private JLabel lblMontoc;
	private JScrollPane scrollPane;
	private JLabel lblSaldos;
	private JTable table;
	private cliente cliente;
	DefaultTableModel modelMovs=new DefaultTableModel();
	examen.cuenta cuenta;
	ArrayList<cliente>listaClientes=new ArrayList<cliente>();
	ArrayList<String>listaTipoCuenta=new ArrayList<String>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Banco window = new Banco();
					window.frmExamen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Banco() {
		initialize();
		modelMovs.addColumn("CUENTA");
		modelMovs.addColumn("FECHA");
		modelMovs.addColumn("TIPO");
		modelMovs.addColumn("MONTO");
		table.setModel(modelMovs);
	}
	public String aMoneda(double cantidad){
		cantidad=Math.round(cantidad*100.0)/100.0;
		DecimalFormat formato=new DecimalFormat("$ #,###.## MXN");
	    return formato.format(cantidad);
	}
	public void verDatos() {
		cliente = listaClientes.get(cboTipocuenta.getSelectedIndex());
		lblNombresc.setText(cliente.getNombre());
		lbltelc.setText(cliente.getTelefono());
		lblDirecc.setText(cliente.getDireccion());
	    
	    if(cliente.getMiscuentas().size()>0) {
	    	cuenta=cliente.getMiscuentas().get(cboTipocuenta.getSelectedIndex());
	    	lblTipoc.setText(cuenta.getTipocuenta());
	    	lblMontoc.setText(aMoneda(cuenta.getMontoinicial()));
	    }else {
	    	lblTipoc.setText("NO HAY CUENTA");
	    	lblMontoc.setText("NO HAY CUENTA");
	    }
	}
	public void verMovimientos() {
		cliente = listaClientes.get(cboClientes.getSelectedIndex());
		cuenta=cliente.getMiscuentas().get(cboCuenta.getSelectedIndex());
	    double saldo=0;
	    while(modelMovs.getRowCount()>0) {
	    	modelMovs.removeRow(0);
	    }
	    for(movimiento m: cuenta.getMismovimientos()) {
	    	Object mov[]=new Object[4];
	    	mov[0]=cuenta.getTipocuenta();
	    	mov[1]=m.getFechaMovimiento();
	    	mov[2]=m.getTipoMovimiento();
	    	mov[4]=aMoneda(m.getMonto());
	    	saldo+=m.getMonto();
	    	modelMovs.addRow(mov);
	    }
	    table.setModel(modelMovs);
	    lblSaldos .setText(aMoneda(saldo));
	}
	public void refrescarComboCuentas() {
		cliente = listaClientes.get(cboTipocuenta.getSelectedIndex());
		int i=0;
		ArrayList<String> cuentas=new ArrayList<String>();
		for (cuenta c: cliente.getMiscuentas()) {
			cuentas.add(c.getTipocuenta());
		}
		cboTipocuenta.setModel(new DefaultComboBoxModel(cuentas.toArray()));
		
	}
	public void borrarFormCliente() {
		txtNombre.setText("");
		txtelefono.setText("");
		txtDireccion.setText("");
	}
	public void llenarCombosCliente() {
		Object clientes[]=new Object [listaClientes.size()];
		int i=0;
		for(cliente c : listaClientes) {
			clientes[i]=c.getNombre();
	        i++;
		}
		cboCliente.setModel(new DefaultComboBoxModel(clientes));
		cboClientes.setModel(new DefaultComboBoxModel(clientes));
	}
	public void llenarCombosTipoCuenta(){
		Object tipos[]=new Object[listaTipoCuenta.size()];
		int i=0;
		for(String tipo : listaTipoCuenta) {
			tipos[i]=tipo;
			i++;
	}
		cboCuenta.setModel(new DefaultComboBoxModel(tipos));
		cboTipocuenta.setModel(new DefaultComboBoxModel(tipos));
	}
	public void borrarFormCuenta() {
		cboCliente.setSelectedIndex(0);
		cboCuenta.setSelectedIndex(0);
		txtmonto.setText("");
	}
	private void initialize() {
		frmExamen = new JFrame();
		frmExamen.setTitle("Examen");
		frmExamen.setBounds(100, 100, 665, 422);
		frmExamen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExamen.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Banco CECYTEM");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 131, 20);
		frmExamen.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Banco.class.getResource("/Img/perrocecy.jpg")));
		lblNewLabel_1.setBounds(10, 42, 131, 89);
		frmExamen.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "NUEVO CLIENTE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(151, 11, 202, 153);
		frmExamen.getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("NOMBRE");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 23, 63, 20);
		panel.add(lblNewLabel_2);
		
		JLabel hjkghuji = new JLabel("TELEFONO");
		hjkghuji.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
		hjkghuji.setBounds(10, 54, 71, 14);
		panel.add(hjkghuji);
		
		JLabel lblNewLabel_2_1 = new JLabel("DIRECCION");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(10, 79, 71, 14);
		panel.add(lblNewLabel_2_1);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(82, 23, 113, 20);
		panel.add(txtNombre);
		
		txtelefono = new JTextField();
		txtelefono.setColumns(10);
		txtelefono.setBounds(82, 51, 113, 20);
		panel.add(txtelefono);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(82, 79, 113, 20);
		panel.add(txtDireccion);
		
		btnAgregarCliente = new JButton("Agregar Cliente");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente c=new cliente();
				c.setNombre(txtNombre.getText());
				c.setTelefono(txtelefono.getText());
				c.setDireccion(txtDireccion.getText());
				listaClientes.add(c);
				borrarFormCliente();
				llenarCombosCliente();
				
			}
		});
		btnAgregarCliente.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		btnAgregarCliente.setBounds(20, 104, 149, 33);
		panel.add(btnAgregarCliente);
		
		JPanel Panel = new JPanel();
		Panel.setLayout(null);
		Panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "NUEVA CUENTA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Panel.setBounds(362, 11, 277, 153);
		frmExamen.getContentPane().add(Panel);
		
		JLabel lblNewLabel_3 = new JLabel("Cliente");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 27, 73, 14);
		Panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Tipo de Cuenta");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
		lblNewLabel_3_1.setBounds(10, 52, 130, 14);
		Panel.add(lblNewLabel_3_1);
		
		cboCliente = new JComboBox();
		cboCliente.setBounds(93, 24, 128, 22);
		Panel.add(cboCliente);
		
		cboCuenta = new JComboBox();
		cboCuenta.setBounds(10, 66, 130, 22);
		Panel.add(cboCuenta);
		
		btnagredar = new JButton("+");
		btnagredar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipoCuenta=JOptionPane.showInputDialog(this,"TIPO CUENTA");
				listaTipoCuenta.add(tipoCuenta);
				llenarCombosTipoCuenta();
			}
		});
		btnagredar.setBounds(10, 99, 46, 43);
		Panel.add(btnagredar);
		
		JLabel lblNewLabel_4 = new JLabel("Monto");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
		lblNewLabel_4.setBounds(177, 52, 46, 14);
		Panel.add(lblNewLabel_4);
		
		txtmonto = new JTextField();
		txtmonto.setColumns(10);
		txtmonto.setBounds(151, 66, 116, 22);
		Panel.add(txtmonto);
		
		btnagregarcuenta = new JButton("Agregar Cuenta");
		btnagregarcuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente=listaClientes.get(cboClientes.getSelectedIndex());
				cuenta cuenta=new cuenta();
				cuenta.setTipocuenta(listaTipoCuenta.get(cboCuenta.getSelectedIndex()));
				cuenta.setMontoinicial(Double.parseDouble(txtmonto.getText()));
				cliente.addCuenta(cuenta);
				movimiento m=new movimiento();
				m.setFechaMovimiento(new SimpleDateFormat("dd/MM/yyyy").format(new Date(0)));
				m.setTipoMovimiento("Apertura");
				m.setMonto(Double.parseDouble(txtmonto.getText()));
			    cuenta.addMovimiento(m);
			    borrarFormCuenta();
			    refrescarComboCuentas();
			}
		});
		btnagregarcuenta.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		btnagregarcuenta.setBounds(66, 99, 201, 43);
		Panel.add(btnagregarcuenta);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "MOVIMIENTOS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 163, 629, 74);
		frmExamen.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_5 = new JLabel("Clientes");
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(10, 22, 37, 14);
		panel_1.add(lblNewLabel_5);
		
		cboClientes = new JComboBox();
		cboClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente = listaClientes.get(cboTipocuenta.getSelectedIndex());
				refrescarComboCuentas();
				verMovimientos();
				verDatos();
			}
		});
		cboClientes.setBounds(51, 19, 94, 22);
		panel_1.add(cboClientes);
		
		JLabel lblNewLabel_5_1 = new JLabel("Tipos de cuenta");
		lblNewLabel_5_1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		lblNewLabel_5_1.setBounds(151, 22, 75, 14);
		panel_1.add(lblNewLabel_5_1);
		
		cboTipocuenta = new JComboBox();
		cboTipocuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verMovimientos();
				verDatos();
			}
			
		});
		cboTipocuenta.setBounds(228, 19, 94, 22);
		panel_1.add(cboTipocuenta);
		
		JLabel lblNewLabel_5_2 = new JLabel("Tipo de movimiento");
		lblNewLabel_5_2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		lblNewLabel_5_2.setBounds(327, 22, 117, 14);
		panel_1.add(lblNewLabel_5_2);
		
		cboMovimiento = new JComboBox();
		cboMovimiento.setBounds(423, 19, 117, 22);
		panel_1.add(cboMovimiento);
		
		JLabel lblNewLabel_4_1 = new JLabel("Monto");
		lblNewLabel_4_1.setBounds(560, 23, 46, 14);
		panel_1.add(lblNewLabel_4_1);
		
		txtxmonto = new JTextField();
		txtxmonto.setColumns(10);
		txtxmonto.setBounds(533, 47, 86, 20);
		panel_1.add(txtxmonto);
		
		btnAgregarMovimiento = new JButton("Agregar Movimiento");
		btnAgregarMovimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente = listaClientes.get(cboClientes.getSelectedIndex());
				cuenta=cliente.getMiscuentas().get(cboCuenta.getSelectedIndex());
				movimiento m=new movimiento();
				m.setFechaMovimiento(new SimpleDateFormat("dd/MM/yyyy").format(new Date(0)));
				m.setTipoMovimiento(cboMovimiento.getSelectedItem().toString());
				double monto=Double.parseDouble(txtxmonto.getText().toString());
				monto=m.getTipoMovimiento().endsWith("Depocito")?monto:(monto*-1);
				m.setMonto(monto);
				cuenta.addMovimiento(m);
				verMovimientos();
			}
		});
		btnAgregarMovimiento.setBounds(10, 47, 194, 23);
		panel_1.add(btnAgregarMovimiento);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "DATOS DE CUENTA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 241, 193, 131);
		frmExamen.getContentPane().add(panel_2);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		lblNombre.setBounds(10, 23, 76, 14);
		panel_2.add(lblNombre);
		
		lblNombresc = new JLabel("");
		lblNombresc.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNombresc.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		lblNombresc.setBounds(96, 23, 87, 14);
		panel_2.add(lblNombresc);
		
		JLabel lblNewLabel_6_1 = new JLabel("Direccion");
		lblNewLabel_6_1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		lblNewLabel_6_1.setBounds(10, 68, 76, 14);
		panel_2.add(lblNewLabel_6_1);
		
		lbltelc = new JLabel("");
		lbltelc.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbltelc.setBounds(96, 43, 87, 14);
		panel_2.add(lbltelc);
		
		JLabel lblNewLabel_6_2 = new JLabel("Telefono");
		lblNewLabel_6_2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		lblNewLabel_6_2.setBounds(10, 43, 76, 14);
		panel_2.add(lblNewLabel_6_2);
		
		lblDirecc = new JLabel("");
		lblDirecc.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDirecc.setBounds(96, 68, 87, 14);
		panel_2.add(lblDirecc);
		
		JLabel lblNewLabel_6_3 = new JLabel("Tipo de Cuenta");
		lblNewLabel_6_3.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		lblNewLabel_6_3.setBounds(10, 87, 76, 14);
		panel_2.add(lblNewLabel_6_3);
		
		lblTipoc = new JLabel("");
		lblTipoc.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTipoc.setBounds(96, 93, 87, 14);
		panel_2.add(lblTipoc);
		
		JLabel lblNewLabel_6_4 = new JLabel("Monto Inicial");
		lblNewLabel_6_4.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		lblNewLabel_6_4.setBounds(10, 103, 76, 17);
		panel_2.add(lblNewLabel_6_4);
		
		JLabel lblTipoCliente = new JLabel("");
		lblTipoCliente.setBounds(96, 87, 87, 14);
		panel_2.add(lblTipoCliente);
		
		lblMontoc = new JLabel("");
		lblMontoc.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMontoc.setBounds(96, 106, 87, 14);
		panel_2.add(lblMontoc);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 248, 426, 108);
		frmExamen.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Cuenta", "Fecha", "Tipo", "Monto"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblSaldo_1 = new JLabel("Saldo");
		lblSaldo_1.setBounds(416, 356, 41, 16);
		frmExamen.getContentPane().add(lblSaldo_1);
		
		lblSaldos = new JLabel("10,000,00 MXN");
		lblSaldos.setBounds(482, 356, 91, 16);
		frmExamen.getContentPane().add(lblSaldos);
	}
}
