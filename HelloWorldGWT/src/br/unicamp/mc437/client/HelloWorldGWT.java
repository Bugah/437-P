package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.unicamp.mc437.client.datatypes.Administrador;
import br.unicamp.mc437.client.datatypes.Produto;
import br.unicamp.mc437.client.datatypes.SubCategoria;
import br.unicamp.mc437.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author ©2014 gustavo waku - MC437 example
 **/


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloWorldGWT implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final InserirProdutoServiceAsync inserirProdutoService = GWT
			.create(InserirProdutoService.class);
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final SubCategoriaServiceAsync subCatService = GWT
			.create(SubCategoriaService.class);
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Buscar");
		final TextBox searchField = new TextBox();
		searchField.setText("Digite sua busca aqui...");
		
		final ListBox searchOn	= new ListBox();
		searchOn.addItem("Produto");
		searchOn.addItem("Descrição");
		final Label errorLabel = new Label();
		
		final Map<Integer, String> imgs_url = new HashMap<Integer, String>();
		
		final ListBox rangePrecos = new ListBox();	// Item Indexes
		rangePrecos.addItem("Todos");				//0
		rangePrecos.addItem("Menor que R$10");		//1
		rangePrecos.addItem("Entre R$10 e R$20");	//2
		rangePrecos.addItem("Entre R$20 e R$40");	//3
		rangePrecos.addItem("Entre R$40 e R$60");	//4
		rangePrecos.addItem("Maior que R$60");		//5
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the searchField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("searchFieldContainer").add(searchField);
		RootPanel.get("searchOnContainer").add(searchOn);
		RootPanel.get("rangePrecosContainer").add(rangePrecos);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		final ScrollPanel r = new ScrollPanel();
		final HTML h = new HTML();
		RootPanel.get("resultsContainer").add(r);
		// Focus the cursor on the name field when the app loads
		searchField.setFocus(true);
		searchField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		
		 
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and searchField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the searchField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the searchField to the server and wait for a response.
			 */
			private String seeCategory() {
				int i = searchOn.getSelectedIndex();
				
				switch(i){
				case 0: return "nome";
				case 1: return "descricao";
				default: return "Error";
				}
			}
			
			private int seeRangePrecos() {
				int i = rangePrecos.getSelectedIndex(); 
				return i;
			}
			
			
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = searchField.getText().trim();
				r.clear();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Entre com pelo menos 4 caracteres");
					return;
				}
				if ( FieldVerifier.containsSpecial(textToServer) ){
					errorLabel.setText("Procure sem caracteres especiais");
					return;
				}
				
				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				
				Produto p = new Produto();
				p.setNome(textToServer);

				
				sendButton.setEnabled(true);
				
				
				
				// INSERIR INTERFACE DE BUSCA AQUI //
				//int total;
				greetingService.greetServer(p,seeCategory(), imgs_url, new AsyncCallback<ArrayList<Produto>>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}
					public void onSuccess(ArrayList<Produto> result) {
						String html = "<table>";					
						for(Produto i : result){
							switch(seeRangePrecos()) {
							case 0: {
								//html = html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
								html = html+"<tr><td><img src =\""+i.getUrlImagemUnica()+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
								break;
							}
							case 1: {
								if(i.getPreco() < 10) 
								//	html = html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
								html = html+"<tr><td><img src =\""+i.getUrlImagemUnica()+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
								break;
							}
							case 2: {
								if( i.getPreco() >= 10 && i.getPreco() < 20 )
								//	html = html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
									html = html+"<tr><td><img src =\""+i.getUrlImagemUnica()+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
								break;
							}
							case 3: {
								if( i.getPreco() >= 20 && i.getPreco() < 40 )
								//	html = html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
									html = html+"<tr><td><img src =\""+i.getUrlImagemUnica()+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
								break;
							}
							case 4: {
								if( i.getPreco() >= 40 && i.getPreco() < 60 )
								//	html = html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
									html = html+"<tr><td><img src =\""+i.getUrlImagemUnica()+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
								break;
							}
							case 5: {
								if( i.getPreco() >= 60 )
									//html = html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
									html = html+"<tr><td><img src =\""+i.getUrlImagemUnica()+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preço: "+Double.toString(i.getPreco())+"</td></tr>";
								break;
							}
							}
							
						}
						html = html+"</table>";
						h.setHTML(html);
						r.add(h);
						closeButton.setFocus(true);
					}
				});
				
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		searchField.addKeyUpHandler(handler);
	
	
		// parte inserir produto
		
		final ListBox subCats	= new ListBox(true);
		final TextBox productName = new TextBox();
		RootPanel.get("productName").add(productName);
		final TextBox productDescription = new TextBox();
		RootPanel.get("productDescription").add(productDescription);
		final TextBox productPrice = new TextBox();
		RootPanel.get("productPrice").add(productPrice);
		final TextBox productPicture = new TextBox();
		RootPanel.get("productPicture").add(productPicture);
		final TextBox productStorage = new TextBox();
		RootPanel.get("productStorage").add(productStorage);
		
		
		final Button sendProduct = new Button("Cadastrar");
		sendProduct.addStyleName("sendButton");
		RootPanel.get("productButton").add(sendProduct);
		

		subCatService.getSubCategorias( new AsyncCallback<ArrayList<HashMap<String, String>>>() {
			public void onFailure(Throwable caught) {
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);
			}


			@Override
			public void onSuccess(ArrayList<HashMap<String, String>> result) {
				for(int i=0;i<result.size();i++){
					HashMap<String, String> hashMap = result.get(i);
					subCats.addItem(hashMap.get("name")+" ("+hashMap.get("catName")+")", hashMap.get("id"));
				}
				RootPanel.get("subCats").add(subCats);		
			}
		});
		

		
		class HandlerCadastrarProduto implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				inserirProduto();
			}

			/**
			 * Fired when the user types in the searchField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					inserirProduto();
				}
			}




			
			private void inserirProduto() {
				// First, we validate the input.
				errorLabel.setText("");
				
				String productNameStr = productName.getText();
				sendButton.setEnabled(false);
		
				
				Produto p= new Produto();
				Administrador admin = new Administrador();
				//modificar quando tivermos administradores
				admin.setId(1);
				p.setAdmin(admin);
				p.setDeletado(0);
				p.setDescricao(productDescription.getText());
				p.setEstoque(Integer.valueOf(productStorage.getText()));
				p.setId(0);
				p.setNome(productNameStr);
				p.setPreco(Double.valueOf(productPrice.getText()));
				p.setPrecoPromocional(00.00);
				p.setUrlImagemUnica(productPicture.getText());
				ArrayList<SubCategoria> subCatsAL = new ArrayList<SubCategoria>();
		for(int i=0;i<subCats.getItemCount();i++){
			if(subCats.isItemSelected(i)){
				SubCategoria subCatObj = new SubCategoria();
				subCatObj.setId(Integer.valueOf(subCats.getValue(i)));
				subCatsAL.add(subCatObj);
			}
		}
			
				p.setSubCat(subCatsAL);
				

			
				sendButton.setEnabled(true);
				
				
				
				// INSERIR INTERFACE DE BUSCA AQUI //				
				inserirProdutoService.inserirProdutoServer(p, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
			
			
			
			
		}
		
		HandlerCadastrarProduto handlerCadastrarProduto = new HandlerCadastrarProduto();
		sendProduct.addClickHandler(handlerCadastrarProduto);
		
		
		//parte login
		final TextBox loginUsername = new TextBox();
		RootPanel.get("loginUsername").add(loginUsername);
		loginUsername.setText("Nome de usu�rio");
		final TextBox loginSenha = new TextBox();
		RootPanel.get("loginSenha").add(loginSenha);
		loginSenha.setText("Senha");
		
		final Button loginButton = new Button("Entrar");
		loginButton.addStyleName("loginButton");
		RootPanel.get("loginButton").add(loginButton);

		class HandlerLogin implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the loginButton.
			 */
			public void onClick(ClickEvent event) {
				login();
			}

			/**
			 * Fired when the user types in the loginSenha.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					login();
				}
			}
			
			private void login() {
				// First, we validate the input.
				errorLabel.setText("");
				
				String productNameStr = productName.getText();
				loginButton.setEnabled(true);				
				
				// INSERIR INTERFACE DE BUSCA AQUI //				
				loginService.loginCliente(loginUsername.getText(),loginSenha.getText(),new AsyncCallback<Boolean>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(Boolean ok) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML("Voc� est� logado no sistema!");
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
		}
		HandlerLogin handlerLogin = new HandlerLogin();
		loginButton.addClickHandler(handlerLogin);
	}
}
