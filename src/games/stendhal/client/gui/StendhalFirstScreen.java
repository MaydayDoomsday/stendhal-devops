/* $Id$ */
/***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.client.gui;

import games.stendhal.client.StendhalClient;
import games.stendhal.client.stendhal;
import games.stendhal.common.Version;

import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


/**
 * Summary description for LoginGUI
 * 
 */
public class StendhalFirstScreen extends JFrame {
	private static final long serialVersionUID = -7825572598938892220L;

	private StendhalClient client;

	private Image background;

	public StendhalFirstScreen(StendhalClient client) {
		super();
		this.client = client;

		URL url = this.getClass().getClassLoader().getResource(
				"data/gui/StendhalSplash.jpg");
		ImageIcon imageIcon = new ImageIcon(url);
		background = imageIcon.getImage();

		initializeComponent();

		this.setVisible(true);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Windows Form Designer. Otherwise, retrieving design
	 * might not work properly. Tip: If you must revise this method, please
	 * backup this GUI file for JFrameBuilder to retrieve your design properly
	 * in future, before revising this method.
	 */
	private void initializeComponent() {
		this.setContentPane(new JPanel() {
			private static final long serialVersionUID = -4272347652159225492L;

			{
				setOpaque(false);
				this.setPreferredSize(new Dimension(640, 480));
			}

			public void paint(Graphics g) {
				g.drawImage(background, 0, 0, this);
				super.paint(g);
			}
		});

		//
		// loginButton
		//
		JButton loginButton = new JButton();
		loginButton.setText("Login to Stendhal");
		loginButton.setMnemonic(KeyEvent.VK_L);
		loginButton.setToolTipText("Press this button to login to a Stendhal server");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		//
		// createAccountButton
		//
		JButton createAccountButton = new JButton();
		createAccountButton.setText("Create an account");
		createAccountButton.setMnemonic(KeyEvent.VK_C);
		createAccountButton.setToolTipText("Press this button to create an account on a Stendhal server.");
		createAccountButton.setEnabled(true);
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount();
			}
		});
		//
		// exitButton
		//
		JButton exitButton = new JButton();
		exitButton.setText("Exit");
		exitButton.setMnemonic(KeyEvent.VK_X);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		//
		// contentPane
		//
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);

		// Hack: Give C code a change to get access to the x11-connection
		// by letting its paint-method beeing invoked by awt. 
		add(X11KeyConfig.get());

		addComponent(contentPane, loginButton, 220, 340, 200, 32);
		addComponent(contentPane, createAccountButton, 220, 380, 200, 32);
		addComponent(contentPane, exitButton, 220, 420, 200, 32);

		//
		// LoginGUI
		//
		setTitle("Stendhal " + stendhal.VERSION + " - a multiplayer online game using Arianne");
		this.setLocation(new Point(100, 100));

		URL url = this.getClass().getClassLoader().getResource("data/gui/StendhalIcon.png");
		this.setIconImage(new ImageIcon(url).getImage());
		pack();
	}

	private void login() {
		try {
			URL url = new URL(stendhal.VERSION_LOCATION);
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
            connection.setConnectTimeout(1500);  // 1.5 secs

			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String version = br.readLine();

			if (Version.compare(stendhal.VERSION, version) < -1) {
				// custom title, warning icon
				JOptionPane
						.showMessageDialog(
								null,
								"Your client is out of date. Latest version is "
										+ version + ". But you are using " + stendhal.VERSION
										+ ".\nDownload from http://arianne.sourceforge.net",
								"Client out of date",
								JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
		}
		new LoginDialog(StendhalFirstScreen.this, client);
	}

	public void createAccount() {
		try {
			URL url = new URL(stendhal.VERSION_LOCATION);
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String version = br.readLine();

			if (Version.compare(version, stendhal.VERSION) < -1) {
				// custom title, warning icon
				JOptionPane
						.showMessageDialog(
								null,
								"Your client is out of date. Latest version is "
										+ version + ". But you are using " + stendhal.VERSION
										+ ".\nDownload from http://arianne.sourceforge.net",
								"Client out of date",
								JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
		}
		new CreateAccountDialog(StendhalFirstScreen.this, client);
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		container.add(c);
	}

}
