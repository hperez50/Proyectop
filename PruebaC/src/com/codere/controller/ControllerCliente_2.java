/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codere.controller;

import com.codere.dao.ClienteDaoImpl;
import com.codere.idao.IClienteDAO;
import com.codere.model.Cliente;
import com.codere.vista.ViewCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author heibe
 */
public class ControllerCliente_2 implements ActionListener {

    ClienteDaoImpl objP;
    ViewCliente objV;
    Cliente cliente = new Cliente();

    public ControllerCliente_2() {
        this.objP = new ClienteDaoImpl();
        this.objV = new ViewCliente();

        objV.getBtnEliminar().addActionListener(this);
        objV.getBtnCrear().addActionListener(this);
        objV.getBtnConsultar().addActionListener(this);
        objV.getBtnGuardar().addActionListener(this);
        objV.getBtnEditar().addActionListener(this);
        objV.getBtnLimpiar().addActionListener(this);

    }

    public void iniciar() {
        objV.setTitle("Ejemplo");
        objV.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == objV.getBtnEliminar()) {

            String v = objV.getTxtId().getText();
            System.out.println("*********El valor del P es: " + v);

            if (v.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor digite un valor en el campo 'id' ");
                return;
            }

            int p2 = objV.getBoxRol().getSelectedIndex();

            if (objV.getTxtNombre_1().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor digite un valor en el campo 'Nombre' ");
                return;
            }

            if (p2 == 0) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione una opción del campo 'Rol' ");
                return;
            }

            if (objV.getBtnValidacion().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un valor en el campo 'Activo' ");
                return;
            }

            //Consultar nuevamente en la base de datos
            DefaultTableModel modeloT = new DefaultTableModel();
            // tablaD.setModel(modeloT);
            objV.jtDatos.setModel(modeloT);

            modeloT.addColumn("Id");
            modeloT.addColumn("Nombre");
            modeloT.addColumn("Rol");
            modeloT.addColumn("Activo");

            Object[] columna = new Object[4];

            //int numeroRegistros = modeloCRUD.listPersona().size();
            List<Cliente> clientes = new ArrayList<Cliente>();
            IClienteDAO dao = new ClienteDaoImpl();
            clientes = dao.obtener();

            int numeroRegistros = clientes.size();
            System.out.println("Numero de registros: " + numeroRegistros);

            String[] arr = new String[1];
            List<String> testList = new ArrayList<>(Arrays.asList(arr));

            for (int i = 0; i < numeroRegistros; i++) {

                columna[0] = objP.obtener().get(i).getIdUsuario();
                columna[1] = objP.obtener().get(i).getNombre();
                columna[2] = objP.obtener().get(i).getIdRol();
                columna[3] = objP.obtener().get(i).getActivo();

                modeloT.addRow(columna);

                String c = columna[0].toString();
                testList.add(c);

                // }
            }

            String v1 = objV.getTxtId().getText();
            String v2 = v1.trim();
            System.out.println("*********El valor del ID es: " + v1);
            int h = testList.indexOf(v2);
            System.out.println("*********El valor del INDEXOF es: " + h);

            if (h == -1) {

                DefaultTableModel modeloVF = new DefaultTableModel();
                // tablaD.setModel(modeloT);
                objV.jtDatos.setModel(modeloVF);

                modeloVF.addColumn("Id");
                modeloVF.addColumn("Nombre");
                modeloVF.addColumn("Rol");
                modeloVF.addColumn("Activo");

                JOptionPane.showMessageDialog(null, "El ID del usuario no existe en la base de datos, por favor validar");
                return;
            }

            cliente.setNombre(objV.getTxtNombre_1().getText());

            String x = objV.getTxtNombre_1().getText();

            if (x.isEmpty()) {
                System.out.println("*********Ingreso a la validación de usuarios iguales");
                JOptionPane.showMessageDialog(null, "Por favor digite un valor en el campo 'Nombre'");

                return;
            }

            eliminar(cliente);

            DefaultTableModel modeloT2 = new DefaultTableModel();
            // tablaD.setModel(modeloT);
            objV.jtDatos.setModel(modeloT2);

            modeloT2.addColumn("Id");
            modeloT2.addColumn("Nombre");
            modeloT2.addColumn("Rol");
            modeloT2.addColumn("Activo");

            ////Nueva validación
            //Consultar nuevamente en la base de datos
            DefaultTableModel modeloT3 = new DefaultTableModel();
            // tablaD.setModel(modeloT);
            objV.jtDatos.setModel(modeloT3);

            modeloT3.addColumn("Id");
            modeloT3.addColumn("Nombre");
            modeloT3.addColumn("Rol");
            modeloT3.addColumn("Activo");

            Object[] columna2 = new Object[4];

            List<Cliente> clientes2 = new ArrayList<Cliente>();
            IClienteDAO dao2 = new ClienteDaoImpl();
            clientes = dao2.obtener();

            int numeroRegistros2 = clientes.size();
            System.out.println("Numero de registros: " + numeroRegistros2);

            for (int i = 0; i < numeroRegistros2; i++) {

                columna[0] = objP.obtener().get(i).getIdUsuario();
                columna[1] = objP.obtener().get(i).getNombre();
                columna[2] = objP.obtener().get(i).getIdRol();
                columna[3] = objP.obtener().get(i).getActivo();

                modeloT3.addRow(columna);

                // }
            }

            JOptionPane.showMessageDialog(null, "Usuario eliminado");

        }

        // Opcion de limpiar
        if (e.getSource() == objV.getBtnLimpiar()) {
            DefaultTableModel modeloT = new DefaultTableModel();
            // tablaD.setModel(modeloT);
            objV.jtDatos.setModel(modeloT);

            modeloT.addColumn("Id");
            modeloT.addColumn("Nombre");
            modeloT.addColumn("Rol");
            modeloT.addColumn("Activo");

        }

        // Opcion guardar
        if (e.getSource() == objV.getBtnGuardar()) {

            //Consultar nuevamente en la base de datos
            DefaultTableModel modeloT = new DefaultTableModel();

            //modeloT.addColumn("Id");
            modeloT.addColumn("Nombre");
            modeloT.addColumn("Rol");
            modeloT.addColumn("Activo");

            Object[] columna = new Object[4];

            //int numeroRegistros = modeloCRUD.listPersona().size();
            List<Cliente> clientes = new ArrayList<Cliente>();
            IClienteDAO dao = new ClienteDaoImpl();
            clientes = dao.obtener();

            int numeroRegistros = clientes.size();
            System.out.println("Numero de registros: " + numeroRegistros);

            String[] arr = new String[1];

            List<String> testList = new ArrayList<>(Arrays.asList(arr));

            for (int i = 0; i < numeroRegistros; i++) {

                columna[0] = objP.obtener().get(i).getIdUsuario();
                columna[1] = objP.obtener().get(i).getNombre();
                columna[2] = objP.obtener().get(i).getIdRol();
                columna[3] = objP.obtener().get(i).getActivo();

                //modeloT.addRow(columna);
                //System.out.println("El valor de COLUMNA es: " +  columna[1]);
                String n = objV.getTxtNombre_1().getText();
                String n1 = n.trim();
                String n2 = columna[1].toString();
                String n3 = n2.trim();

                if (n1.equals(n3)) {
                    System.out.println("*********Ingreso a la validación de usuarios iguales");
                    JOptionPane.showMessageDialog(null, "El usuario debe ser unico, por favor validar");
                    return;
                }

            }

            //int p2 = objV.getBoxRol().getSelectedIndex();
            //String p = objV.getTxtId().getText();
            //int pt = Integer.parseInt(p);
            int p2 = objV.getBoxRol().getSelectedIndex();

            //System.out.println("*********El valor del P es: "  + p  );
            if (objV.getTxtNombre_1().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor digite un valor en el campo 'Nombre' ");
                return;
            }

            if (p2 == 0) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione una opción del campo 'Rol' ");
                return;
            }

            if (objV.getBtnValidacion().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un valor en el campo 'Activo' ");
                return;
            }

            //int pt2 = Integer.parseInt(p2);
            // guarda un cliente a través del controlador
            //cliente.setIdUsuario(pt);
            cliente.setNombre(objV.getTxtNombre_1().getText());
            cliente.setActivo(objV.getBtnValidacion());
            cliente.setIdRol(p2);

            registrar(cliente);

            JOptionPane.showMessageDialog(null, "Usuario creado");
        }

        ///Editar usuarios
        if (e.getSource() == objV.getBtnEditar()) {

            String v = objV.getTxtId().getText();
            System.out.println("*********El valor del P es: " + v);

            if (v.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor digite un valor en el campo 'id' ");
                return;
            }

            DefaultTableModel modeloT = new DefaultTableModel();

            modeloT.addColumn("Id");
            modeloT.addColumn("Nombre");
            modeloT.addColumn("Rol");
            modeloT.addColumn("Activo");

            Object[] columna = new Object[4];

            //int numeroRegistros = modeloCRUD.listPersona().size();
            List<Cliente> clientes = new ArrayList<Cliente>();
            IClienteDAO dao = new ClienteDaoImpl();
            clientes = dao.obtener();

            int numeroRegistros = clientes.size();
            System.out.println("Numero de registros: " + numeroRegistros);

            String[] arr = new String[1];

            List<String> testList = new ArrayList<>(Arrays.asList(arr));

            for (int i = 0; i < numeroRegistros; i++) {

                columna[0] = objP.obtener().get(i).getIdUsuario();
                columna[1] = objP.obtener().get(i).getNombre();
                columna[2] = objP.obtener().get(i).getIdRol();
                columna[3] = objP.obtener().get(i).getActivo();

                //modeloT.addRow(columna);
                String c = columna[0].toString();
                testList.add(c);

                //System.out.println("El valor de COLUMNA es: " +  columna[1]);
                String n = objV.getTxtNombre_1().getText();
                String n1 = n.trim();
                String n2 = columna[1].toString();
                String n3 = n2.trim();

                String n4 = columna[0].toString();
                String n5 = n4.trim();

                String p = objV.getTxtId().getText();
                String p1 = p.trim();
                //int pt = Integer.parseInt(p);

                if (n1.equals(n3)) {
                    System.out.println("*********El valor de N5 es " + n5);
                    System.out.println("*********El valor de p1 es " + p1);

                    if (n5.equals(p1)) {

                    } else {
                        System.out.println("*********Ingreso a la validación de usuarios iguales 2");
                        JOptionPane.showMessageDialog(null, "El nombre de usuario es unico, por favor validar en la base de datos");
                        return;
                    }

                }

            }

            String p = objV.getTxtId().getText();
            System.out.println("*********El valor del P es: " + p);

            //if (p.isEmpty()) {
            //  JOptionPane.showMessageDialog(null, "Por favor digite un valor en el campo 'id' ");
            // return;
            ///}
            int pt = Integer.parseInt(p);
            String pv = p.trim();

            int p2 = objV.getBoxRol().getSelectedIndex();
            //int pt2 = Integer.parseInt(p2);

            if (objV.getTxtNombre_1().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor digite un valor en el campo 'Nombre' ");
                return;
            }

            if (p2 == 0) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione una opción del campo 'Rol' ");
                return;
            }

            if (objV.getBtnValidacion().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un valor en el campo 'Activo' ");
                return;
            }

            // Actualizar información del cliente
            cliente.setIdUsuario(pt);
            cliente.setNombre(objV.getTxtNombre_1().getText());
            cliente.setIdRol(p2);
            cliente.setActivo(objV.getBtnValidacion());
            System.out.println("*********El valor del rol es: " + p2);

            actualizar(cliente);

            JOptionPane.showMessageDialog(null, "Usuario actualizado");

        }

        if (e.getSource() == objV.getBtnConsultar()) {

            DefaultTableModel modeloT = new DefaultTableModel();

            objV.jtDatos.setModel(modeloT);

            modeloT.addColumn("Id");
            modeloT.addColumn("Nombre");
            modeloT.addColumn("Rol");
            modeloT.addColumn("Activo");

            Object[] columna = new Object[4];

            //int numeroRegistros = modeloCRUD.listPersona().size();
            List<Cliente> clientes = new ArrayList<Cliente>();
            IClienteDAO dao = new ClienteDaoImpl();
            clientes = dao.obtener();

            int numeroRegistros = clientes.size();
            System.out.println("Numero de registros: " + numeroRegistros);

            if (numeroRegistros == 0) {
                JOptionPane.showMessageDialog(null, "No existen registros en la base de datos");
            }

            for (int i = 0; i < numeroRegistros; i++) {

                columna[0] = objP.obtener().get(i).getIdUsuario();
                columna[1] = objP.obtener().get(i).getNombre();
                columna[2] = objP.obtener().get(i).getIdRol();
                columna[3] = objP.obtener().get(i).getActivo();

                //System.out.println("El valor de COLUMNA es: " +  columna[1]);
                String n = objV.getTxtNombre().getText();
                String n1 = n.trim();
                String n2 = columna[1].toString();
                String n3 = n2.trim();

                if (n1.isEmpty()) {
                    System.out.println("*********EL CAMPO NOMBRE ESTA VACIO: ");
                    modeloT.addRow(columna);
                } else {

                    if (n1.equals(n2)) {
                        System.out.println("*********INGRESO A LA VALIDACIÓN DEL IF: ");
                        modeloT.addRow(columna);
                        return;
                        //System. exit(0);
                    } else {
                        System.out.println("*********INGRESO A LA VALIDACIÓN DEL ELSE: ");
                        System.out.println("*********ELVALOR DE I ES: " + i);

                        if (i == (numeroRegistros - 1)) {
                            System.out.println("*********INGRESO A LA VALIDACIÓN DEL ELSE---- RETURN: ");
                            JOptionPane.showMessageDialog(null, "El valor consultado no existe en la base de datos");
                        }

                    }

                }

                // }
            }

            System.out.println("El valor de NOMBRE es: " + objV.getTxtNombre().getText());
            System.out.println("Los datos de la tabla son: " + modeloT);

        }

    }

    public void eliminar(Cliente cliente) {
        IClienteDAO dao = new ClienteDaoImpl();
        dao.eliminar(cliente);

    }

    //llama al DAO para guardar un cliente
    public void registrar(Cliente cliente) {
        IClienteDAO dao = new ClienteDaoImpl();
        dao.registrar(cliente);
    }

    public void actualizar(Cliente cliente) {
        IClienteDAO dao = new ClienteDaoImpl();
        dao.actualizar(cliente);
    }

}
