package pack1;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JFrame;
import java.awt.Font;

public class HospitalManagement extends JFrame {
	
	private JPanel contentPane;
	
	private JTable patientTable;
    private DefaultTableModel patientModel;
    private JTextField txtPatientId, txtPatientName, txtPatientSurname, txtPatientDiagnosis;
    
    private JTable doctorTable;
    private DefaultTableModel doctorModel;
    private JTextField txtDoctorId, txtDoctorName, txtDoctorSurname, txtDoctorSpecialty;
    
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/hospital";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "your_password_here";
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
    
    public void fillPatientTable() throws SQLException {
        patientModel.setColumnCount(0);
        patientModel.setRowCount(0);
        
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM patient");
 
        int colNum = rs.getMetaData().getColumnCount();
        
        for (int i = 1; i <= colNum; i++) {
            patientModel.addColumn(rs.getMetaData().getColumnName(i));
        }
        
        while (rs.next()) {
            patientModel.addRow(new Object[]{
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
            });
        }
        
        con.close();
    }
    
    public void addPatient(int id, String name, String surname, String diagnosis)
            throws SQLException {
        
        Connection con = getConnection();
        
        String query = "INSERT INTO patient (patientId, name, surname, diagnosis) VALUES (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, surname);
        ps.setString(4, diagnosis);
        
        ps.executeUpdate();
        con.close();
    }
    
    public void deletePatient(int id) throws SQLException {
        Connection con = getConnection();
        
        String query = "DELETE FROM patient WHERE patientId=?";
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setInt(1, id);
        ps.executeUpdate();
        
        con.close();
    }
    
    public void updatePatient(int id, String name, String surname, String diagnosis)
            throws SQLException {
        
        Connection con = getConnection();
        
        String query = "UPDATE patient SET name=?, surname=?, diagnosis=? WHERE patientId=?";
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setString(1, name);
        ps.setString(2, surname);
        ps.setString(3, diagnosis);
        ps.setInt(4, id);
        
        ps.executeUpdate();
        con.close();
    }
    
    public void fillDoctorTable() throws SQLException {
        doctorModel.setColumnCount(0);
        doctorModel.setRowCount(0);
 
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM doctor");
 
        int colNum = rs.getMetaData().getColumnCount();
        
        for (int i = 1; i <= colNum; i++) {
            doctorModel.addColumn(rs.getMetaData().getColumnName(i));
        }
        
        while (rs.next()) {
            doctorModel.addRow(new Object[]{
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
            });
        }
        
        con.close();
    }
    
    public void addDoctor(int id, String name, String surname, String specialty)
            throws SQLException {
        
        Connection con = getConnection();
        
        String query = "INSERT INTO doctor (doctorId, name, surname, specialty) VALUES (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, surname);
        ps.setString(4, specialty);
        
        ps.executeUpdate();
        con.close();
    }
    
    public void deleteDoctor(int id) throws SQLException {
        Connection con = getConnection();
        
        String query = "DELETE FROM doctor WHERE doctorId=?";
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setInt(1, id);
        ps.executeUpdate();
        
        con.close();
    }
    
    public void updateDoctor(int id, String name, String surname, String specialty)
            throws SQLException {
        
        Connection con = getConnection();
        
        String query = "UPDATE doctor SET name=?, surname=?, specialty=? WHERE doctorId=?";
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setString(1, name);
        ps.setString(2, surname);
        ps.setString(3, specialty);
        ps.setInt(4, id);
        
        ps.executeUpdate();
        con.close();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HospitalManagement frame = new HospitalManagement();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    public HospitalManagement() {
        setTitle("Hospital Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 595, 410);
 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 604, 460);
        contentPane.add(tabbedPane);
        
        JPanel patientPanel = new JPanel();
        patientPanel.setLayout(null);
        tabbedPane.addTab("Patients", patientPanel);
        
        patientModel = new DefaultTableModel();
        patientTable = new JTable(patientModel);
        JScrollPane patientScroll = new JScrollPane(patientTable);
        patientScroll.setBounds(28, 10, 524, 150);
        patientPanel.add(patientScroll);
        
        JLabel lblPId = new JLabel("ID:");
        lblPId.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 12));
        lblPId.setBounds(56, 175, 60, 23);
        patientPanel.add(lblPId);
        
        txtPatientId = new JTextField();
        txtPatientId.setBounds(136, 176, 120, 23);
        patientPanel.add(txtPatientId);
        
        JLabel lblPName = new JLabel("Name:");
        lblPName.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 12));
        lblPName.setBounds(313, 175, 60, 23);
        patientPanel.add(lblPName);
 
        txtPatientName = new JTextField();
        txtPatientName.setBounds(393, 175, 120, 23);
        patientPanel.add(txtPatientName);
 
        JLabel lblPSurname = new JLabel("Surname:");
        lblPSurname.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 12));
        lblPSurname.setBounds(56, 215, 70, 23);
        patientPanel.add(lblPSurname);
        
        txtPatientSurname = new JTextField();
        txtPatientSurname.setBounds(136, 215, 120, 23);
        patientPanel.add(txtPatientSurname);
 
        JLabel lblPDiagnosis = new JLabel("Diagnosis:");
        lblPDiagnosis.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 12));
        lblPDiagnosis.setBounds(313, 215, 70, 23);
        patientPanel.add(lblPDiagnosis);
 
        txtPatientDiagnosis = new JTextField();
        txtPatientDiagnosis.setBounds(393, 215, 120, 23);
        patientPanel.add(txtPatientDiagnosis);
        
        JButton btnListPatients = new JButton("List");
        btnListPatients.setBounds(56, 270, 89, 23);
        btnListPatients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    fillPatientTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
                }
            }
        });
        patientPanel.add(btnListPatients);
        
        JButton btnAddPatient = new JButton("Add");
        btnAddPatient.setBounds(189, 270, 89, 23);
        btnAddPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                    addPatient(
                        Integer.parseInt(txtPatientId.getText()),
                        txtPatientName.getText(),
                        txtPatientSurname.getText(),
                        txtPatientDiagnosis.getText()
                    );
                    
                    fillPatientTable();
                    JOptionPane.showMessageDialog(null, "Patient Added!");
                    
                    txtPatientId.setText("");
                    txtPatientName.setText("");
                    txtPatientSurname.setText("");
                    txtPatientDiagnosis.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID must be a number.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
                }
            }
        });
        patientPanel.add(btnAddPatient);
        
        JButton btnDeletePatient = new JButton("Delete");
        btnDeletePatient.setBounds(311, 270, 89, 23);
        btnDeletePatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = patientTable.getSelectedRow();
                
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                    return;
                }
                
                int id = Integer.parseInt(patientTable.getValueAt(selectedRow, 0).toString());
                
                try {
                    deletePatient(id);
                    fillPatientTable();
                    JOptionPane.showMessageDialog(null, "Patient Deleted!");
                    
                    txtPatientId.setText("");
                    txtPatientName.setText("");
                    txtPatientSurname.setText("");
                    txtPatientDiagnosis.setText("");
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
                }
            }
        });
        patientPanel.add(btnDeletePatient);
        
        JButton btnUpdatePatient = new JButton("Update");
        btnUpdatePatient.setBounds(436, 270, 89, 23);
        btnUpdatePatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = patientTable.getSelectedRow();
                
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to update.");
                    return;
                }
                
                int id = Integer.parseInt(txtPatientId.getText());
                
                try {
                    updatePatient(
                        id,
                        txtPatientName.getText(),
                        txtPatientSurname.getText(),
                        txtPatientDiagnosis.getText()
                    );
                    
                    fillPatientTable();
                    JOptionPane.showMessageDialog(null, "Patient Updated!");
                    
                    txtPatientId.setText("");
                    txtPatientName.setText("");
                    txtPatientSurname.setText("");
                    txtPatientDiagnosis.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID must be a number.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
                }
            }
        });
        patientPanel.add(btnUpdatePatient);
        
        patientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = patientTable.getSelectedRow();
                
                if (row >= 0) {
                    txtPatientId.setText(patientTable.getValueAt(row, 0).toString());
                    txtPatientName.setText(patientTable.getValueAt(row, 1).toString());
                    txtPatientSurname.setText(patientTable.getValueAt(row, 2).toString());
                    txtPatientDiagnosis.setText(patientTable.getValueAt(row, 3).toString());
                }
            }
        });
        
        JPanel doctorPanel = new JPanel();
        doctorPanel.setLayout(null);
        tabbedPane.addTab("Doctors", doctorPanel);
        
        doctorModel = new DefaultTableModel();
        doctorTable = new JTable(doctorModel);
        JScrollPane doctorScroll = new JScrollPane(doctorTable);
        doctorScroll.setBounds(28, 10, 524, 150);
        doctorPanel.add(doctorScroll);
        
        JLabel lblDId = new JLabel("ID:");
        lblDId.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 12));
        lblDId.setBounds(56, 175, 60, 23);
        doctorPanel.add(lblDId);
        
        txtDoctorId = new JTextField();
        txtDoctorId.setBounds(136, 176, 120, 23);
        doctorPanel.add(txtDoctorId);
        
        JLabel lblDName = new JLabel("Name:");
        lblDName.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 12));
        lblDName.setBounds(313, 175, 60, 23);
        doctorPanel.add(lblDName);
        
        txtDoctorName = new JTextField();
        txtDoctorName.setBounds(393, 175, 120, 23);
        doctorPanel.add(txtDoctorName);
 
        JLabel lblDSurname = new JLabel("Surname:");
        lblDSurname.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 12));
        lblDSurname.setBounds(56, 215, 70, 23);
        doctorPanel.add(lblDSurname);
 
        txtDoctorSurname = new JTextField();
        txtDoctorSurname.setBounds(136, 215, 120, 23);
        doctorPanel.add(txtDoctorSurname);
 
        JLabel lblDSpecialty = new JLabel("Specialty:");
        lblDSpecialty.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 12));
        lblDSpecialty.setBounds(313, 215, 70, 23);
        doctorPanel.add(lblDSpecialty);
 
        txtDoctorSpecialty = new JTextField();
        txtDoctorSpecialty.setBounds(393, 215, 120, 23);
        doctorPanel.add(txtDoctorSpecialty);
        
        JButton btnListDoctors = new JButton("List");
        btnListDoctors.setBounds(56, 270, 89, 23);
        btnListDoctors.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    fillDoctorTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
                }
            }
        });
        doctorPanel.add(btnListDoctors);
        
        JButton btnAddDoctor = new JButton("Add");
        btnAddDoctor.setBounds(189, 270, 89, 23);
        btnAddDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addDoctor(
                        Integer.parseInt(txtDoctorId.getText()),
                        txtDoctorName.getText(),
                        txtDoctorSurname.getText(),
                        txtDoctorSpecialty.getText()
                    );
                    
                    fillDoctorTable();
                    JOptionPane.showMessageDialog(null, "Doctor Added!");
                    
                    txtDoctorId.setText("");
                    txtDoctorName.setText("");
                    txtDoctorSurname.setText("");
                    txtDoctorSpecialty.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID must be a number.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
                }
            }
        });
        doctorPanel.add(btnAddDoctor);
        
        JButton btnDeleteDoctor = new JButton("Delete");
        btnDeleteDoctor.setBounds(311, 270, 89, 23);
        btnDeleteDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = doctorTable.getSelectedRow();
                
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                    return;
                }
                
                int id = Integer.parseInt(doctorTable.getValueAt(selectedRow, 0).toString());
                
                try {
                    deleteDoctor(id);
                    fillDoctorTable();
                    JOptionPane.showMessageDialog(null, "Doctor Deleted!");
                    
                    txtDoctorId.setText("");
                    txtDoctorName.setText("");
                    txtDoctorSurname.setText("");
                    txtDoctorSpecialty.setText("");
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
                }
            }
        });
        doctorPanel.add(btnDeleteDoctor);
       
        JButton btnUpdateDoctor = new JButton("Update");
        btnUpdateDoctor.setBounds(436, 270, 89, 23);
        btnUpdateDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = doctorTable.getSelectedRow();
                
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to update.");
                    return;
                }
                
                int id = Integer.parseInt(txtDoctorId.getText());
                
                try {
                    updateDoctor(
                        id,
                        txtDoctorName.getText(),
                        txtDoctorSurname.getText(),
                        txtDoctorSpecialty.getText()
                    );
                    
                    fillDoctorTable();
                    JOptionPane.showMessageDialog(null, "Doctor Updated!");
                    
                    txtDoctorId.setText("");
                    txtDoctorName.setText("");
                    txtDoctorSurname.setText("");
                    txtDoctorSpecialty.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID must be a number.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
                }
            }
        });
        doctorPanel.add(btnUpdateDoctor);
        
        doctorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = doctorTable.getSelectedRow();
                
                if (row >= 0) {
                    txtDoctorId.setText(doctorTable.getValueAt(row, 0).toString());
                    txtDoctorName.setText(doctorTable.getValueAt(row, 1).toString());
                    txtDoctorSurname.setText(doctorTable.getValueAt(row, 2).toString());
                    txtDoctorSpecialty.setText(doctorTable.getValueAt(row, 3).toString());
                }
            }
        });
    }
}

