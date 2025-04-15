import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FontForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JCheckBox chckbxBold;
	private JCheckBox chckbxItalic;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JList<String> list;

	DefaultListModel<String> mdl = new DefaultListModel<>();
	private int startFontSize = 800;
	private final int border = 10 ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FontForm frame = new FontForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void refreshFontLabel() {
		int bold = chckbxBold.isSelected() ? 1 : 0;
		int italic = chckbxItalic.isSelected() ? 1 : 0;
		int fontstyle = bold * Font.BOLD + italic * Font.ITALIC;
		String name = list.getSelectedValue();
		lblNewLabel.setText(textField.getText());
		int fontSize = startFontSize;

		do {
			Font font = new Font(name, fontstyle, fontSize);
			lblNewLabel.setFont(font);
			// System.out.println(lblNewLabel.getSize());
			// System.out.println(lblNewLabel.getPreferredSize());
			fontSize--;
		} while (lblNewLabel.getPreferredSize().width > lblNewLabel.getSize().width
				|| lblNewLabel.getPreferredSize().height > lblNewLabel.getSize().height);

	}

	/**
	 * Create the frame.
	 */
	public FontForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 674);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setBounds(border,2*border, 192, 35);
		contentPane.add(textField);
		textField.setColumns(10);

		String[] fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		btnNewButton = new JButton("МЯУ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				if (text.length() == 1) {
					mdl.clear();
					for (String name : fontFamilies) {
						Font font = new Font(name, Font.PLAIN, 24);
						if (font.canDisplayUpTo(text) == -1) {
							mdl.addElement(name);
						}
					}
					if (mdl.size() > 0) {
						Font font = new Font(mdl.get(0), Font.PLAIN, 24);
						textField.setFont(font);
					}
					System.out.println(lblNewLabel.getPreferredSize());
					System.out.println(lblNewLabel.getSize());

				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setBounds(border,textField.getY()+textField.getHeight() +border, 192, 44);
		contentPane.add(btnNewButton);

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshFontLabel();
			}
		};

		chckbxBold = new JCheckBox("Bold");
		chckbxBold.addActionListener(actionListener);
		chckbxBold.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxBold.setBounds(283, btnNewButton.getY(), 93, 21);
		contentPane.add(chckbxBold);

		chckbxItalic = new JCheckBox("Italic");
		chckbxItalic.addActionListener(actionListener);
		chckbxItalic.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxItalic.setBounds(442, btnNewButton.getY(), 93, 21);
		contentPane.add(chckbxItalic);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		int panelBorderX = contentPane.getBorder().getBorderInsets(contentPane).left;
		int panelBorderY = contentPane.getBorder().getBorderInsets(contentPane).left;
		//int f= this.get
		//System.out.println(contentPane);
		int spw = (this.getWidth()-2*panelBorderX-3*border)/2;
		int spy =  btnNewButton.getY()+btnNewButton.getHeight()+2*border ;
		int sph = (this.getHeight()-2*panelBorderY-spy-border);
		scrollPane.setBounds(border,spy , spw, sph);
		contentPane.add(scrollPane);

		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 160));
		lblNewLabel.setBounds(spw+2*border, scrollPane.getY(), spw, 448);

		contentPane.add(lblNewLabel);

		list = new JList<>(mdl);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				refreshFontLabel();
			}
		});
		scrollPane.setViewportView(list);

	}
}
