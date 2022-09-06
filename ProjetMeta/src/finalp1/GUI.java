package finalp1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

public class GUI extends JFrame {
	private JTextField init;
	public static String EI, EB;
	public static int c;
	public static List LBFS = new ArrayList<>(); 
	public static List LDFS = new ArrayList<>(); 
	public static List LABD = new ArrayList<>(); 
	public static List LAMD = new ArrayList<>(); 
	public static List<String> LGA = new ArrayList<String>(); 
	public static List<String> LPSO = new ArrayList<String>(); 
	boolean done = false;
	public static JTextArea textAreaMOVES ;
	public static JFormattedTextField frmtdtxtfldDonnerProfondeur;
	long tempsDebut, tempsFin;
    double seconds;
	static JTextArea textArea = new JTextArea();
	
	public static int val; 
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static int isSolvable(String state){
	    int s = 0;
	    int curr, others;
	    for(int i=0; i<state.length(); i++){
	        curr = Character.getNumericValue(state.charAt(i));
	        if(curr != 0) {
	            for (int j = i + 1; j < state.length(); j++) {
	                others = Character.getNumericValue(state.charAt(j));
	                if (curr > others && others != 0) {
	                    s++;
	                }
	            }
	        }
	    }
	   return s;
	}

	public void shuffle(JButton b1, JButton b2) {
		String step = b2.getText();
		if (step.equals("0")) {
			b2.setText(b1.getText());
			b1.setText("0");
		}
	}
	
	public void chooseS(String step) {
		switch(step) {
		case "left":		 
	    	   if (case2.getText().equals("0")) {
	    		   shuffle(case1, case2);
	    		   check();
	    		   break;
	    	   } else if (case3.getText().equals("0")) {
	    		   shuffle(case2, case3);
	    		   check();
	    		   break;
	    	   }else if (case5.getText().equals("0")) {
	    		   shuffle(case4, case5);
	    		   check();
	    		   break;
	    	   }else if (case6.getText().equals("0")) {
	    		   shuffle(case5, case6);
	    		   check();
	    		   break;
	    	   }else if (case8.getText().equals("0")) {
	    		   shuffle(case7, case8);
	    		   check();
	    		   break;
	    	   }else if (caseX.getText().equals("0")) {
		         shuffle(case8, caseX);
		         check();
		         break;
	    	   }
	   
		case "right":	
 			   if (case1.getText().equals("0")) {
	    		   shuffle(case2, case1);
	    		   check();
			       break;
	    	   }else if (case2.getText().equals("0")) {
	    		   shuffle(case3, case2);
	    		   check();
	    		   break;
	    	   }else if (case4.getText().equals("0")) {
	    		   shuffle(case5, case4);
	    		   check();
	    		   break;
	    	   }else if (case5.getText().equals("0")) {
	    		   shuffle(case6, case5);
	    		   check();
	    		   break;
	    	   }else if (case7.getText().equals("0")) {
	    		   shuffle(case8, case7);
	    		   check();
	    		   break;
	    	   }else if (case8.getText().equals("0")) {
	    		   shuffle(caseX, case8);
	    		   check();
	    		   break;
	    	   }
	   
		case "up":	
			if (case4.getText().equals("0")) {
	    		   shuffle(case1, case4);
	    		   check();
	    		   break;
	    	   }else if (case5.getText().equals("0")) {
	    		   shuffle(case2, case5);
	    		   check();
	    		   break;
	    	   }else if (case6.getText().equals("0")) {
	    		   shuffle(case3, case6);
	    		   check();
	    		   break;
	    	   }else if (case7.getText().equals("0")) {
	    		   shuffle(case4, case7);
	    		   check();
	    		   break;
	    	   }else if (case8.getText().equals("0")) {
	    		   shuffle(case5, case8);
	    		   check();
	    		   break;
	    	   }else if (caseX.getText().equals("0")) {
	    		   shuffle(case6, caseX);
	    		   check();
	    		   break;
	    	   }
	    	   break;
		
		case "down":		
			if (case1.getText().equals("0")) {
	    		   shuffle(case4, case1);
	    		   check();
	    		   break;
	    	   }else if (case2.getText().equals("0")) {
	    		   shuffle(case5, case2);
	    		   check();
	    		   break;
	    	   }else if (case3.getText().equals("0")) {
	    		   shuffle(case6, case3);
	    		   check();
	    		   break;
	    	   }else if (case4.getText().equals("0")) {
	    		   shuffle(case7, case4);
	    		   check();
	    		   break;
	    	   }else if (case5.getText().equals("0")) {
	    		   shuffle(case8, case5);
	    		   check();
	    		   break;
	    	   }else if (case6.getText().equals("0")) {
	    		   shuffle(caseX, case6);
	    		   check();
	    		   break;
	    	   }
	    	   break;
		   
	}}
	
	public void check() {
		if (case1.getText().equals("0")) {
			case1.setEnabled(false);
			case2.setEnabled(true);
			case3.setEnabled(true);
			case4.setEnabled(true);
			case5.setEnabled(true);
			case6.setEnabled(true);
			case7.setEnabled(true);
			case8.setEnabled(true);
			caseX.setEnabled(true);
			}
		else if (case2.getText().equals("0")) {
			case2.setEnabled(false);
			case1.setEnabled(true);
			case3.setEnabled(true);
			case4.setEnabled(true);
			case5.setEnabled(true);
			case6.setEnabled(true);
			case7.setEnabled(true);
			case8.setEnabled(true);
			caseX.setEnabled(true);
			}
		else if (case3.getText().equals("0")) {
			case3.setEnabled(false);
			case1.setEnabled(true);
			case2.setEnabled(true);
			case4.setEnabled(true);
			case5.setEnabled(true);
			case6.setEnabled(true);
			case7.setEnabled(true);
			case8.setEnabled(true);
			caseX.setEnabled(true);
			}					
		else if (case4.getText().equals("0")) {
			case4.setEnabled(false);
			case1.setEnabled(true);
			case2.setEnabled(true);
			case3.setEnabled(true);
			case5.setEnabled(true);
			case6.setEnabled(true);
			case7.setEnabled(true);
			case8.setEnabled(true);
			caseX.setEnabled(true);
			}
		else if (case5.getText().equals("0")) {
			case5.setEnabled(false);
			case1.setEnabled(true);
			case2.setEnabled(true);
			case3.setEnabled(true);
			case4.setEnabled(true);
			case6.setEnabled(true);
			case7.setEnabled(true);
			case8.setEnabled(true);
			caseX.setEnabled(true);
			}
		else if (case6.getText().equals("0")) {
			case6.setEnabled(false);
			case1.setEnabled(true);
			case2.setEnabled(true);
			case3.setEnabled(true);
			case4.setEnabled(true);
			case5.setEnabled(true);
			case7.setEnabled(true);
			case8.setEnabled(true);
			caseX.setEnabled(true);
			}
		else if (case7.getText().equals("0")) {
			case7.setEnabled(false);
			case1.setEnabled(true);
			case2.setEnabled(true);
			case3.setEnabled(true);
			case4.setEnabled(true);
			case5.setEnabled(true);
			case6.setEnabled(true);
			case8.setEnabled(true);
			caseX.setEnabled(true);
			}
		else if (case8.getText().equals("0")) {
			case8.setEnabled(false);
			case1.setEnabled(true);
			case2.setEnabled(true);
			case3.setEnabled(true);
			case4.setEnabled(true);
			case5.setEnabled(true);
			case6.setEnabled(true);
			case7.setEnabled(true);
			caseX.setEnabled(true);
			}
		else if (caseX.getText().equals("0")) {
			caseX.setEnabled(false);
			case1.setEnabled(true);
			case2.setEnabled(true);
			case3.setEnabled(true);
			case4.setEnabled(true);
			case5.setEnabled(true);
			case6.setEnabled(true);
			case7.setEnabled(true);
			case8.setEnabled(true);
			}
	}
	    	   
	/**
	 * Create the frame.
	 * @throws InterruptedException 
	 */
	
	

private static JButton case1 = new JButton("1");
private static JButton case4 = new JButton("4");
private static JButton case5 = new JButton("5");
private static JButton case6 = new JButton("6");
private static JButton case7 = new JButton("7");
private static JButton case8 = new JButton("8");
private static JButton caseX = new JButton("0");
private static JButton case3 = new JButton("3");
private static JButton case2 = new JButton("2");
private final JButton btnStart = new JButton("Ex\u00E9cuter");
private JTextField goal;
private final JTable table = new JTable();
private final JScrollPane scrollPane = new JScrollPane();
private JFormattedTextField amountField;
	public GUI() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 999, 731);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(10, 100, 0, 0));
		panel.setBounds(506, 10, 469, 674);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		

		NumberFormat integerFieldFormatter;

		integerFieldFormatter = NumberFormat.getIntegerInstance();
		integerFieldFormatter.setMaximumFractionDigits(0);
		
		

		frmtdtxtfldDonnerProfondeur = new JFormattedTextField(integerFieldFormatter );
		frmtdtxtfldDonnerProfondeur.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmtdtxtfldDonnerProfondeur.setText("100");
		
		frmtdtxtfldDonnerProfondeur.setToolTipText("Donner profondeur");
		frmtdtxtfldDonnerProfondeur.setBounds(259, 296, 176, 33);
		panel.add(frmtdtxtfldDonnerProfondeur);
		
		
		 javax.swing.ToolTipManager.sharedInstance().setInitialDelay(0);


		JLabel affSolve = new JLabel("");
		affSolve.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		affSolve.setFont(new Font("Tahoma", Font.PLAIN, 20));
		affSolve.setBounds(259, 189, 176, 50);
		panel.add(affSolve);
		
		JButton btnNewButton = new JButton("Tester la solvabilit\u00E9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int pariteI = isSolvable(init.getText().replace("0", ""));
					int pariteB = isSolvable(goal.getText().replace("0", ""));
					Boolean solve ;
			        if ((pariteI % 2) == 0  && (pariteB % 2) == 0) {			
			        	 affSolve.setText("Solvable");
						 affSolve.setForeground(Color.GREEN);
					} else if ((pariteI % 2) != 0  && (pariteB % 2) != 0)  {
						 affSolve.setText("Solvable");
						 affSolve.setForeground(Color.GREEN);						}
					else {
						 affSolve.setText("Pas Solvable");
						 affSolve.setForeground(Color.RED);					}
					 }
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 195, 233, 33);
		panel.add(btnNewButton);
	
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EI = init.getText();
				Verification a1 = new Verification(); 
				int ret = a1.verification(EI);		
				affSolve.setText("");
					
				if (ret == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "Erreur : état doit être de longueur égale à 9.");
				}
				if (ret ==-2) {
					JOptionPane.showMessageDialog(new JFrame(), "Erreur : état doit être numérique seulement et chaque chiffre doit être entre 0 et 8.");
				}
				if (ret == -3) {
					JOptionPane.showMessageDialog(new JFrame(), "Erreur : État doit être de chiffre unique.");
				}
				if (ret == 1) {
										
					case1.setText(String.valueOf(EI.charAt(0)));
					check();
					case2.setText(String.valueOf(EI.charAt(1)));
					check();
					case3.setText(String.valueOf(EI.charAt(2)));
					check();
					case4.setText(String.valueOf(EI.charAt(3)));
					check();
					case5.setText(String.valueOf(EI.charAt(4)));
					check();
					case6.setText(String.valueOf(EI.charAt(5)));
					check();
					case7.setText(String.valueOf(EI.charAt(6)));
					check();
					case8.setText(String.valueOf(EI.charAt(7)));
					check();
					caseX.setText(String.valueOf(EI.charAt(8)));
					check();
					
					caseX.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							shuffle(caseX, case6);
							shuffle(caseX, case8);
						}
					});
					
					case1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							shuffle(case1, case2);
							shuffle(case1, case4);
						}
					});
					
					case8.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							shuffle(case8, caseX);
							shuffle(case8, case7);
							shuffle(case8, case5);
						}
					});
					case4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							shuffle(case4, case1);
							shuffle(case4, case5);
							shuffle(case4, case7);
						}
					});
					
					case7.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							shuffle(case7, case8);
							shuffle(case7, case4);
						}
					});
					case5.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							shuffle(case5, case2);
							shuffle(case5, case4);
							shuffle(case5, case6);
							shuffle(case5, case8);
						}
					});
					case3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							shuffle(case3, case2);
							shuffle(case3, case6);
						}
					});
				
					case6.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							shuffle(case6, case3);
							shuffle(case6, case5);
							shuffle(case6, caseX);
						}
					});
					case2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							shuffle(case2, case1);
							shuffle(case2, case3);
							shuffle(case2, case5);
						}
					});
				}

			}
		});

		caseX.setEnabled(false);
		
		caseX.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		caseX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shuffle(caseX, case6);
				shuffle(caseX, case8);
			}
		});
		case1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		case1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shuffle(case1, case2);
				shuffle(case1, case4);
			}
		});
		case8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));	
		case8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shuffle(case8, caseX);
				shuffle(case8, case7);
				shuffle(case8, case5);
			}
		});
		case4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		case4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shuffle(case4, case1);
				shuffle(case4, case5);
				shuffle(case4, case7);
			}
		});
		case7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));		
		case7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shuffle(case7, case8);
				shuffle(case7, case4);
			}
		});
		case5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		case5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shuffle(case5, case2);
				shuffle(case5, case4);
				shuffle(case5, case6);
				shuffle(case5, case8);
			}
		});
		case3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		case3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shuffle(case3, case2);
				shuffle(case3, case6);
			}
		});
		case6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));	
		case6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shuffle(case6, case3);
				shuffle(case6, case5);
				shuffle(case6, caseX);
			}
		});
		
		  

		

		
		JLabel lblNewLabel = new JLabel("Entrer Etat Initial :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 171, 25);
		panel.add(lblNewLabel);
		
			
		init = new JTextField();
		init.setBounds(10, 45, 203, 35);
		panel.add(init);
		init.setColumns(10);
		

		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnValider.setBounds(259, 47, 103, 33);
		panel.add(btnValider);
		
		JLabel lblChoisissezUnAlgorithme = new JLabel("Choisissez un algorithme :");
		lblChoisissezUnAlgorithme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChoisissezUnAlgorithme.setBounds(10, 259, 233, 25);
		panel.add(lblChoisissezUnAlgorithme);
		
		String[] algos = {"","BFS","DFS","A* Bad Place","A* Manhattan distance","Algo génétique","PSO"};

		JComboBox comboBox = new JComboBox(algos);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(e.getSource()==comboBox) {
					   if(comboBox.getSelectedItem() == "BFS") {
						   frmtdtxtfldDonnerProfondeur.setEnabled(false);
						    tempsDebut = System.currentTimeMillis();				   
						    LBFS = BFS.breadthFirstSearch();
						    tempsFin = System.currentTimeMillis();
						    seconds = (tempsFin - tempsDebut) / 1000F;
					   }
					   if(comboBox.getSelectedItem() == "DFS") {
						   frmtdtxtfldDonnerProfondeur.setEnabled(true);

						   if (frmtdtxtfldDonnerProfondeur.getText().equals("")) {
							   val =100;
						   } else {
							    val = Integer.parseInt(frmtdtxtfldDonnerProfondeur.getText());
						   }
						    tempsDebut = System.currentTimeMillis();
		                    LDFS = DFS.depthFirstSearch();
		                    tempsFin = System.currentTimeMillis();
						    seconds = (tempsFin - tempsDebut) / 1000F;
					   }
					   if(comboBox.getSelectedItem() == "A* Bad Place") 
					   {
						   frmtdtxtfldDonnerProfondeur.setEnabled(false);
						    tempsDebut = System.currentTimeMillis();						   
						    LABD = Astar_final.astar(1);
						    tempsFin = System.currentTimeMillis();
						    seconds = (tempsFin - tempsDebut) / 1000F;
					   }
					   if(comboBox.getSelectedItem() == "A* Manhattan distance") {
						   frmtdtxtfldDonnerProfondeur.setEnabled(false);
						    tempsDebut = System.currentTimeMillis();
		                    LAMD = Astar_final.astar(2);
		                    tempsFin = System.currentTimeMillis();
						    seconds = (tempsFin - tempsDebut) / 1000F;
					   }  
					   if(comboBox.getSelectedItem() == "Algo génétique") {
						   frmtdtxtfldDonnerProfondeur.setEnabled(false);

						    tempsDebut = System.currentTimeMillis();			               		
			                LGA=GA.AlgoG();
  		                    tempsFin = System.currentTimeMillis();
  						    seconds = (tempsFin - tempsDebut) / 1000F;  						
  						    System.out.println("GA "+LGA.size());						 
					   }
					   if(comboBox.getSelectedItem() == "PSO") {
						   frmtdtxtfldDonnerProfondeur.setEnabled(false);

						    tempsDebut = System.currentTimeMillis();			               		
			                LPSO=PSO.PSO();
 		                    tempsFin = System.currentTimeMillis();
 						    seconds = (tempsFin - tempsDebut) / 1000F;  						
 						    System.out.println("PSO "+LPSO.size());						 
 						    System.out.println("PSO "+LPSO);						 
					   }
					   }
		}});//[right, down, right, down, left, left, -]

		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setBounds(10, 294, 182, 35);
		panel.add(comboBox);	
		
		JRadioButton slow = new JRadioButton("Lent");
		slow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		slow.setBounds(10, 347, 83, 33);
		panel.add(slow);
		slow.setSelected(true);
		JRadioButton quick = new JRadioButton("Rapide");
		quick.setFont(new Font("Tahoma", Font.PLAIN, 20));
		quick.setBounds(10, 382, 89, 33);
		panel.add(quick);


		ButtonGroup group = new ButtonGroup();
		group.add(slow);
		group.add(quick);
		
		JButton btnArreter = new JButton("Arreter");
		
		btnArreter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnArreter.setBounds(321, 380, 127, 33);
		panel.add(btnArreter);
		
		JScrollPane sp = new JScrollPane();
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setBounds(10, 593, 486, 91);
	//	panel.add(sp);
		
		
		textAreaMOVES = new JTextArea();
		textAreaMOVES.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaMOVES.setBounds(10, 593, 486, 91);
		sp.setViewportView(textAreaMOVES);
		contentPane.add(sp);
		 btnStart.addActionListener(new ActionListener() {
             private int i = 0;
             private int t = 1000;             
            
             @Override
             public void actionPerformed(ActionEvent e) {
            	
            	 if (quick.isSelected()) {
                	 t=400;
                 }
                 Timer timer = new Timer(t, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {                   	 

                    	 if(comboBox.getSelectedItem() == "BFS") {
                    		 textAreaMOVES.setText("BFS : "+LBFS.toString());
	                    	 if (i >= LBFS.size()) {
	                             ((Timer)(e.getSource())).stop();
	                             i=0;
	                             DefaultTableModel model = (DefaultTableModel)table.getModel();
	                             model.addRow(new Object [] {comboBox.getSelectedItem(), seconds, LBFS.size(), BFS.OPEN_bfs.size()+BFS.CLOSED_bfs.size(), BFS.CLOSED_bfs.size() });                           
	                             BFS.doClear();
	                             LBFS.clear();
	                            
	            		   }else {    
		             		     chooseS(LBFS.get(i).toString());
		             		     i++;}  
                          }
                    	  else if(comboBox.getSelectedItem() == "DFS") {
                     		 textAreaMOVES.setText("DFS : "+LDFS.toString());
	                    	 if (i >= LDFS.size()) {
	                             ((Timer)(e.getSource())).stop();
	                             i=0;
	                             DefaultTableModel model = (DefaultTableModel)table.getModel();
	                             model.addRow(new Object [] {comboBox.getSelectedItem(), seconds, LDFS.size(), DFS.OPEN.size()+DFS.CLOSED.size(), DFS.CLOSED.size()});
	                             DFS.doClear();
	                             LDFS.clear();
	            		     }else {    
		             		     chooseS(LDFS.get(i).toString());
		             		     i++;}  
                          }
                    	  else if(comboBox.getSelectedItem() == "A* Bad Place") {
                     		 textAreaMOVES.setText("A* Bad Place : "+LABD.toString());
 	                    	 if (i >= LABD.size()) {
 	                             ((Timer)(e.getSource())).stop();
 	                             i=0;
 	                             DefaultTableModel model = (DefaultTableModel)table.getModel();
	                             model.addRow(new Object [] {comboBox.getSelectedItem(), seconds, LABD.size(), Astar_final.open.size()+Astar_final.close.size(), Astar_final.close.size()});
	                             LABD.clear();
	                             Astar_final.doClear();
 	            		     }else {    
 		             		     chooseS(LABD.get(i).toString());
 		             		     i++;}  
                          }
                    	  else if(comboBox.getSelectedItem() == "A* Manhattan distance") {
                     		 textAreaMOVES.setText("A* Manhattan distance : " +LAMD.toString());
  	                    	 if (i >= LAMD.size()) {
  	                             ((Timer)(e.getSource())).stop();
  	                             i=0;
  	                             DefaultTableModel model = (DefaultTableModel)table.getModel();
	                             model.addRow(new Object [] {comboBox.getSelectedItem(), seconds, LAMD.size(), Astar_final.open.size()+Astar_final.close.size(), Astar_final.close.size()});
	                             LAMD.clear();
	                             Astar_final.doClear();
  	            		     }else {  
  		             		     chooseS(LAMD.get(i).toString());
  		             		     i++;}  
                            }
                    	  else if(comboBox.getSelectedItem() == "Algo génétique") {     
                     		    textAreaMOVES.setText("Algo génétique : "+LGA.toString());
                    		if (i >= LGA.size()) {
                    			System.out.println("LGA"+LGA);
   	                            ((Timer)(e.getSource())).stop();
   	                            i=0;
   	                            DefaultTableModel model = (DefaultTableModel)table.getModel();
 	                            model.addRow(new Object [] {comboBox.getSelectedItem(), seconds, LGA.size()});
 	                            LGA.clear();
 	                            GA.doClear();
   	            		    }else {  
   	            		    	System.out.println("chosed move "+ LGA.get(i));
   		             		    chooseS(LGA.get(i));
   		             		    i++;}  
                            } 
                    	 else if(comboBox.getSelectedItem() == "PSO") {     
                   		    	textAreaMOVES.setText("PSO : " +LPSO.toString());
                   		    if (i >= LPSO.size()) {
                   		    	System.out.println("LPSO"+LPSO);
 	                            ((Timer)(e.getSource())).stop();
 	                            i=0;
 	                            DefaultTableModel model = (DefaultTableModel)table.getModel();
	                            model.addRow(new Object [] {comboBox.getSelectedItem(), seconds, LPSO.size()});
	                            LPSO.clear();
 	            		    }else {  
 	            		    	System.out.println("chosed move "+ LPSO.get(i));
 		             		    chooseS(LPSO.get(i).toString());
 		             		    i++;}  
                          } 
                     }});
                 
                 
                 timer.start();
                 btnArreter.addActionListener(new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
          				BFS.doClear();
          				DFS.doClear();
          				Astar_final.doClear();
          				LBFS.clear();
          				LDFS.clear(); 
          				LABD.clear();
          				LAMD.clear(); 
          				LGA.clear();          				
          			}
          		});
             }
             
             
         });
		 
		 
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStart.setBounds(151, 382, 127, 33);
		
		panel.add(btnStart);
		
		JLabel lblNewLabel_1 = new JLabel("Entrer Etat But :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 90, 171, 25);
		panel.add(lblNewLabel_1);
		
		goal = new JTextField();
		goal.setColumns(10);
		goal.setBounds(10, 123, 203, 35);
		panel.add(goal);
		
		JButton btnValider_1 = new JButton("Valider");
		btnValider_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EB = goal.getText();
				Verification a1 = new Verification(); 
				int ret = a1.verification(EB);
				affSolve.setText("");
				if (ret == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "Erreur : état doit être de longueur égale à 9.");
				}
				if (ret ==-2) {
					JOptionPane.showMessageDialog(new JFrame(), "Erreur : état doit être numérique seulement et chaque chiffre doit être entre 0 et 8.");
				}
				if (ret == -3) {
					JOptionPane.showMessageDialog(new JFrame(), "Erreur : État doit être de chiffre unique.");
				}
				if (ret == 1) {
			}
			}});
		btnValider_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnValider_1.setBounds(259, 125, 103, 33);
		panel.add(btnValider_1);
		scrollPane.setBounds(10, 435, 449, 229);
		
		panel.add(scrollPane);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Algo", "Temps(S)", "Nbr mouvements", "Noeud générer", "Noeud developpé"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		

				
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 91, 486, 492);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		
		case1.setFont(new Font("Tahoma", Font.BOLD, 30));
		case1.setBounds(6, 10, 150, 150);
		panel_1.add(case1);
		
		
		case4.setFont(new Font("Tahoma", Font.BOLD, 30));
		case4.setBounds(6, 170, 150, 150);
		panel_1.add(case4);

	
		case5.setFont(new Font("Tahoma", Font.BOLD, 30));
		case5.setBounds(166, 170, 150, 150);
		panel_1.add(case5);

		case6.setFont(new Font("Tahoma", Font.BOLD, 30));
		case6.setBounds(326, 170, 150, 150);
		panel_1.add(case6);
		

		case7.setFont(new Font("Tahoma", Font.BOLD, 30));
		case7.setBounds(6, 330, 150, 150);
		panel_1.add(case7);
		
		
		caseX.setFont(new Font("Tahoma", Font.BOLD, 30));
		caseX.setBounds(326, 330, 150, 150);
		panel_1.add(caseX);
		
		case8.setFont(new Font("Tahoma", Font.BOLD, 30));
		case8.setBounds(166, 330, 150, 150);
		panel_1.add(case8);
		
		case3.setFont(new Font("Tahoma", Font.BOLD, 30));
		case3.setBounds(326, 10, 150, 150);
		panel_1.add(case3);
		
		case2.setBounds(166, 10, 150, 150);
		panel_1.add(case2);
		case2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		case2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shuffle(case2, case1);
				shuffle(case2, case3);
				shuffle(case2, case5);
			}
		});
		
		
		case2.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JLabel lblNewLabel_2 = new JLabel("Taquin 3*3");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 50));
		lblNewLabel_2.setBounds(95, 10, 303, 61);
		contentPane.add(lblNewLabel_2);	
	}
}