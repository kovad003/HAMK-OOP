package com.javainterviewpoint;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JSlider;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GasPump extends JFrame {

	private double selectedPrice, reg91, e10_91, prem95, prem98, regDiesel, premDiesel;
	private String formattedPriceStr;
	private String fuelTypeStr;
	private String fuelAmountStr;
	
	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblPic0;
	private JTextPane txtpn0;
	private JLabel lblPic1;
	private JButton btn91_reg;
	private JButton btn95_prem;
	private JButton btn91_e10;
	private JButton btn98_prem;
	private JButton btnDiesel_reg;
	private JButton btnDiesel_prem;
	private JSlider slider;
	private JLabel lblFuel;
	private JLabel lblPrice;
	private JLabel lblPic2;
	private JButton btnPay;
	private JLabel lblSupport;
	private JPanel panel_3;
	private JLabel lblPic3;
	private JLabel lblTXT3;
	private JButton btnAddGas;
	private JButton btnTakeGas;
	private JLabel lblFinalPrice;
	private JLabel lblFinalFuel;
	private JLabel lblFinalAmount;
	private JPanel panel_4;
	private JButton btnStart;
	private JButton btnOk;
	private JButton btnAbort;
	private JButton btnPBar;
	private JButton btnPBar_1;
	private JButton btnPBar_2;
	private JButton btnPBar_3;
	private JButton btnPBar_4;
	private JButton btnPBar_5;
	private JButton btnPBar_6;
	private JButton btnPBar_7;
	private JButton btnPBar_8;
	private JLabel lblTouchTheScreen;
	private JLabel lblTouchTheScreen_1;
	private JLabel lblSelectionTxt;

	//READ AND WRITE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	ReadCSV_Scanner priceReader = new ReadCSV_Scanner();
	ReceiptWriter receiptWriter = new ReceiptWriter();
	private JLabel lblPaymentRec;
	private JRadioButton rdbtnReceiptPrinting;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GasPump frame = new GasPump();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//METHODS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void printReceipt() throws IOException
	{
		receiptWriter.writeReceipt(formattedPriceStr, fuelTypeStr, fuelAmountStr);
	}
	
	public void checkFuelPrices()
	{
		ArrayList<Double> pricesArrList= new ArrayList<Double>();		
		pricesArrList = priceReader.readAndSavePrices();
		
		reg91 = pricesArrList.get(0);
		e10_91 = pricesArrList.get(1);
		prem95 = pricesArrList.get(2);
		prem98 = pricesArrList.get(3);
		regDiesel = pricesArrList.get(4); 
		premDiesel = pricesArrList.get(5);
	}
	
	public void switchPanels(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();	
	}
	
	public void buttonSelFX(JButton button)
	{
		btn91_reg.setBackground(UIManager.getColor("Button.background"));
		btn91_e10.setBackground(UIManager.getColor("Button.background"));
		btn95_prem.setBackground(UIManager.getColor("Button.background"));
		btn98_prem.setBackground(UIManager.getColor("Button.background"));
		btnDiesel_reg.setBackground(UIManager.getColor("Button.background"));
		btnDiesel_prem.setBackground(UIManager.getColor("Button.background"));
		
		button.setBackground(SystemColor.activeCaption);
	}
	
	public void adjustPriceAmount()
	{
		  DecimalFormat df = new DecimalFormat("#.##");
          formattedPriceStr = df.format(selectedPrice * slider.getValue());
          lblFuel.setText(Integer.toString(slider.getValue()) + " L");
          lblPrice.setText(formattedPriceStr + " �");
          lblFinalPrice.setText(formattedPriceStr + " � in total.");
          fuelTypeStr = returnFuelName();
          lblFinalFuel.setText(fuelTypeStr);
          fuelAmountStr = Integer.toString(slider.getValue());
          lblFinalAmount.setText(fuelAmountStr + " L");
	}
	
	public String returnFuelName()
	{	
		if (selectedPrice == reg91) 
		{
			return "91 regular";
		}
		else if (selectedPrice == e10_91)
		{
			return "91 E10";
		}
		else if (selectedPrice == prem95)
		{
			return "95 premium";
		}
		else if (selectedPrice == prem98)
		{
			return "98 premium";
		}
		else if (selectedPrice == regDiesel)
		{
			return "Diesel regular";
		}
		else if (selectedPrice == premDiesel)
		{
			return "Diesel premium";
		}
		else return "N/A";
	}
		
	/**
	 * Create the frame.
	 */
	public GasPump() {
		
		checkFuelPrices();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 414, 239);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panel = new JPanel();
		layeredPane.add(panel, "name_16629662124200");
		panel.setLayout(null);
		
		lblPic0 = new JLabel("");
		lblPic0.setBounds(10, 5, 171, 193);
		float width0 = lblPic0.getHeight() * (886f/997f);
		Image img = new ImageIcon(this.getClass().getResource("/fuel_station_girl.png")).getImage();
		img = img.getScaledInstance((int) width0, lblPic0.getHeight(), Image.SCALE_SMOOTH);
		lblPic0.setIcon(new ImageIcon(img));
		panel.add(lblPic0);
		
		
		txtpn0 = new JTextPane();
		txtpn0.setBackground(SystemColor.control);
		txtpn0.setEditable(false);
		txtpn0.setText("Hi There! \r\n\r\nI am Victoria, your virtual assistant. Welcome to WeTankU automated fuel station. Please open your tank cap.\r\n\r\nPress START to continue!");
		txtpn0.setBounds(191, 5, 213, 193);
		panel.add(txtpn0);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnReceiptPrinting.setSelected(false);
				btnAbort.setEnabled(true);
				switchPanels(panel_1);
				slider.setValue(1);
				btn91_reg.doClick();
				selectedPrice = reg91;
			}
		});
		btnStart.setFocusable(false);
		btnStart.setBounds(166, 205, 89, 23);
		panel.add(btnStart);
		
		panel_1 = new JPanel();
		layeredPane.add(panel_1, "name_16648073798099");
		panel_1.setLayout(null);
		
		lblPic1 = new JLabel("");
		lblPic1.setBounds(10, 113, 151, 85);
		float width1 = lblPic1.getHeight() * (768f/432f);
		Image img1 = new ImageIcon(this.getClass().getResource("/gasoline.jpg")).getImage();
		img1 = img1.getScaledInstance((int) width1, lblPic1.getHeight(), Image.SCALE_SMOOTH);
		lblPic1.setIcon(new ImageIcon(img1));
		panel_1.add(lblPic1);
		
		btn91_reg = new JButton("91 - regular");
		btn91_reg.setBackground(UIManager.getColor("Button.background"));
		btn91_reg.setFocusable(false);
		btn91_reg.doClick();
		btn91_reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPrice = reg91;
				adjustPriceAmount();
				buttonSelFX(btn91_reg);
				//buttonSelFX(btn91_reg, JButtonArray);
				
			}
		});
		btn91_reg.setBounds(10, 45, 100, 23);
		panel_1.add(btn91_reg);
		
		btn95_prem = new JButton("95 - premium");
		btn95_prem.setBackground(UIManager.getColor("Button.background"));
		btn95_prem.setFocusable(false);
		btn95_prem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPrice = prem95;
				adjustPriceAmount();
				buttonSelFX(btn95_prem);
			}
		});
		btn95_prem.setBounds(230, 45, 100, 23);
		panel_1.add(btn95_prem);
		
		btn91_e10 = new JButton("91 - E10");
		btn91_e10.setBackground(UIManager.getColor("Button.background"));
		btn91_e10.setFocusable(false);
		btn91_e10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPrice = e10_91;
				adjustPriceAmount();
				buttonSelFX(btn91_e10);
			}
		});
		btn91_e10.setBounds(120, 45, 100, 23);
		panel_1.add(btn91_e10);
		
		btn98_prem = new JButton("98 - premium");
		btn98_prem.setBackground(UIManager.getColor("Button.background"));
		btn98_prem.setFocusable(false);
		btn98_prem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPrice = prem98;
				adjustPriceAmount();
				buttonSelFX(btn98_prem);
			}
		});
		btn98_prem.setBounds(10, 79, 100, 23);
		panel_1.add(btn98_prem);
		
		btnDiesel_reg = new JButton("Diesel - regular");
		btnDiesel_reg.setBackground(UIManager.getColor("Button.background"));
		btnDiesel_reg.setFocusable(false);
		btnDiesel_reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPrice = regDiesel;
				adjustPriceAmount();
				buttonSelFX(btnDiesel_reg);
			}
		});
		btnDiesel_reg.setBounds(120, 79, 100, 23);
		panel_1.add(btnDiesel_reg);
		
		btnDiesel_prem = new JButton("Diesel - premium");
		btnDiesel_prem.setBackground(UIManager.getColor("Button.background"));
		btnDiesel_prem.setFocusable(false);
		btnDiesel_prem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPrice = premDiesel;
				adjustPriceAmount();
				buttonSelFX(btnDiesel_prem);
			}
		});
		btnDiesel_prem.setBounds(230, 79, 100, 23);
		panel_1.add(btnDiesel_prem);
		
		slider = new JSlider(0, 100, 1);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setMajorTickSpacing(25);
		slider.setValueIsAdjusting(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				JSlider slider = (JSlider) ce.getSource();
				
				if (slider.getValue() == 0) 
				{
					slider.setValue(1);;
				}
			
	            if (!slider.getValueIsAdjusting())
	               System.out.println(slider.getValue());
	            adjustPriceAmount();
	            
			}
			
		});
		slider.setBackground(Color.LIGHT_GRAY);
		slider.setBounds(355, 11, 49, 187);
		panel_1.add(slider);
		
	
		lblFuel = new JLabel(slider.getValue() + " L");
		lblFuel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFuel.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuel.setForeground(Color.WHITE);
		lblFuel.setOpaque(true);
		lblFuel.setBackground(SystemColor.textHighlight);
		lblFuel.setBounds(223, 159, 67, 39);
		panel_1.add(lblFuel);
		
		
		lblPrice = new JLabel(selectedPrice * slider.getValue() + " �");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(171, 113, 168, 35);
		panel_1.add(lblPrice);
		
		btnAddGas = new JButton("+");
		btnAddGas.setBackground(Color.LIGHT_GRAY);
		btnAddGas.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnAddGas.setForeground(Color.BLACK);
		btnAddGas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider.setValue(slider.getValue()+1);
			}
		});
		btnAddGas.setBounds(292, 159, 49, 39);
		panel_1.add(btnAddGas);
		
		btnTakeGas = new JButton("-");
		btnTakeGas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider.setValue(slider.getValue()-1);
			}
		});
		btnTakeGas.setForeground(Color.BLACK);
		btnTakeGas.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTakeGas.setBackground(Color.LIGHT_GRAY);
		btnTakeGas.setBounds(171, 159, 49, 39);
		panel_1.add(btnTakeGas);
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel_2);
			}
		});
		btnOk.setFocusable(false);
		btnOk.setBounds(167, 205, 89, 23);
		panel_1.add(btnOk);
		
		lblSelectionTxt = new JLabel("Please make your selection.");
		lblSelectionTxt.setBounds(10, 13, 320, 23);
		panel_1.add(lblSelectionTxt);
		
		panel_2 = new JPanel();
		layeredPane.add(panel_2, "name_16702057989199");
		panel_2.setLayout(null);
		
		lblPic2 = new JLabel("");
		lblPic2.setBounds(176, 11, 228, 183);
		float width2 = lblPic2.getHeight() * (1200f/900f);
		Image img2 = new ImageIcon(this.getClass().getResource("/credit.jpg")).getImage();
		img2 = img2.getScaledInstance((int) width2, lblPic2.getHeight(), Image.SCALE_SMOOTH);
		
		lblSupport = new JLabel("0-24 Support Desk:\r\n+358 7 999 1111");
		lblSupport.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupport.setBounds(176, 165, 228, 28);
		panel_2.add(lblSupport);
		lblPic2.setIcon(new ImageIcon(img2));
		panel_2.add(lblPic2);
		
		btnPay = new JButton("Pay & Refuel");
		btnPay.setFocusable(false);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//lblPaymentRec.setVisible(true);
				btnAbort.setEnabled(false);
				btnPBar.doClick();
				if (rdbtnReceiptPrinting.isSelected())
				{
					
					try {
						printReceipt();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnPay.setBounds(32, 168, 117, 23);
		panel_2.add(btnPay);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(10, 11, 156, 120);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		lblFinalAmount = new JLabel("amount");
		lblFinalAmount.setBounds(10, 45, 136, 23);
		panel_4.add(lblFinalAmount);
		lblFinalAmount.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblFinalFuel = new JLabel("fuel");
		lblFinalFuel.setBounds(10, 11, 136, 23);
		panel_4.add(lblFinalFuel);
		lblFinalFuel.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblFinalPrice = new JLabel("price");
		lblFinalPrice.setBounds(10, 77, 136, 23);
		panel_4.add(lblFinalPrice);
		lblFinalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnAbort = new JButton("Abort");
		btnAbort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel);
			}
		});
		btnAbort.setFocusable(false);
		btnAbort.setBounds(32, 202, 117, 23);
		panel_2.add(btnAbort);
		
		btnPBar = new JButton("");
		btnPBar.setBackground(new Color(0, 128, 0));
		btnPBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnPBar_1.doClick();

			}
		});
		btnPBar.setBounds(180, 203, 28, 9);
		panel_2.add(btnPBar);
		
		btnPBar_1 = new JButton("");
		btnPBar_1.setBackground(new Color(0, 128, 0));
		btnPBar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				btnPBar_2.doClick();
			}
		});
		btnPBar_1.setBounds(218, 203, 28, 9);
		panel_2.add(btnPBar_1);
		
		btnPBar_2 = new JButton("");
		btnPBar_2.setBackground(new Color(0, 128, 0));
		btnPBar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnPBar_3.doClick();
			}
		});
		btnPBar_2.setBounds(256, 203, 28, 9);
		panel_2.add(btnPBar_2);
		
		btnPBar_3 = new JButton("");
		btnPBar_3.setBackground(new Color(0, 128, 0));
		btnPBar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				btnPBar_4.doClick();
			}
		});
		btnPBar_3.setBounds(294, 203, 28, 9);
		panel_2.add(btnPBar_3);
		
		btnPBar_4 = new JButton("");
		btnPBar_4.setBackground(new Color(0, 128, 0));
		btnPBar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				btnPBar_5.doClick();
			}
		});
		btnPBar_4.setBounds(332, 203, 28, 9);
		panel_2.add(btnPBar_4);
		
		btnPBar_5 = new JButton("");
		btnPBar_5.setBackground(new Color(0, 128, 0));
		btnPBar_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel_3);
				
			}
		});
		btnPBar_5.setBounds(370, 203, 28, 9);
		panel_2.add(btnPBar_5);
		
		lblPaymentRec = new JLabel("Payment received. Refueling in progress...");
		lblPaymentRec.setVisible(false);
		lblPaymentRec.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymentRec.setBounds(179, 218, 228, 14);
		panel_2.add(lblPaymentRec);
		
		rdbtnReceiptPrinting = new JRadioButton("Print receipt");
		rdbtnReceiptPrinting.setBounds(32, 138, 109, 23);
		panel_2.add(rdbtnReceiptPrinting);
		
		panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPBar_6.doClick();
			}
		});
		layeredPane.add(panel_3, "name_35073525123100");
		panel_3.setLayout(null);
		
		lblPic3 = new JLabel("");
		lblPic3.setBounds(89, 38, 237, 170);
		float width3 = lblPic3.getHeight() * (450f/322f);
		Image img3 = new ImageIcon(this.getClass().getResource("/happy_car.jpg")).getImage();
		img3 = img3.getScaledInstance((int) width3, lblPic3.getHeight(), Image.SCALE_SMOOTH);
		lblPic3.setIcon(new ImageIcon(img3));		
		panel_3.add(lblPic3);
		
		lblTXT3 = new JLabel("WeTankU and see u soon!");
		lblTXT3.setBounds(79, 11, 262, 19);
		lblTXT3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblTXT3);
		
		btnPBar_6 = new JButton("");
		btnPBar_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				btnPBar_7.doClick();
			}
		});
		btnPBar_6.setBackground(new Color(0, 0, 128));
		btnPBar_6.setBounds(156, 219, 28, 9);
		panel_3.add(btnPBar_6);
		
		btnPBar_7 = new JButton("");
		btnPBar_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				btnPBar_8.doClick();
			}
		});
		btnPBar_7.setBackground(new Color(0, 0, 128));
		btnPBar_7.setBounds(194, 219, 28, 9);
		panel_3.add(btnPBar_7);
		
		btnPBar_8 = new JButton("");
		btnPBar_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				switchPanels(panel);
			}
		});
		btnPBar_8.setBackground(new Color(0, 0, 128));
		btnPBar_8.setBounds(232, 219, 28, 9);
		panel_3.add(btnPBar_8);
		
		lblTouchTheScreen = new JLabel("Touch the screen");
		lblTouchTheScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblTouchTheScreen.setBounds(10, 209, 126, 19);
		panel_3.add(lblTouchTheScreen);
		
		lblTouchTheScreen_1 = new JLabel("Touch the screen");
		lblTouchTheScreen_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTouchTheScreen_1.setBounds(278, 209, 126, 19);
		panel_3.add(lblTouchTheScreen_1);
	}
}
