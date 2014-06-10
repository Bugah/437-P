package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unicamp.mc437.client.datatypes.Administrador;
import br.unicamp.mc437.client.datatypes.CarrinhoComprasElemento;
import br.unicamp.mc437.client.datatypes.Produto;
import br.unicamp.mc437.client.datatypes.SubCategoria;
import br.unicamp.mc437.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

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
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final InserirProdutoServiceAsync inserirProdutoService = GWT
			.create(InserirProdutoService.class);

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final SubCategoriaServiceAsync subCatService = GWT
			.create(SubCategoriaService.class);

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);
	
	private final FinalizarCompraServiceAsync finalizarCompraService = GWT
			.create(FinalizarCompraService.class);

	private List<Produto> slotProd = null;

	/**
	 * This is the entry point method.
	 */

	private Integer quant = 0;

	private double totalPrice;
	NumberFormat format = NumberFormat.getFormat("R$ 0.00");
	
	
	public void simulFrete(int length, double totalWF, HTMLPanel h, RadioButton radio){
		double lengthD = (double) length;
		double priceFrete = lengthD/15;
		
		/*
		// ATENCAO, SETAR AS VARIAVEIS ANTES DE CHAMAR A FUNCAO
		CorreioImpl correio = new CorreioImpl();
		
		correio.setCepOrigem("13083755");
		correio.setCepDestino("06525100");
		// SETAR CODIGO DE SERVICO //
		
		/*  40010 SEDEX Varejo 
			40045 SEDEX a Cobrar Varejo 
			40215 SEDEX 10 Varejo 
			40290 SEDEX Hoje Varejo 
			41106 PAC Varejo 	         */
		/*
		double correctPrice = correio.valorFrete();
		
		*/
		
		if(radio.getValue()){
			priceFrete = priceFrete * 2;
		}
		h.getElementById("FCfrete").setInnerHTML(format.format(priceFrete).replaceAll("\\.", "\\,"));
		h.getElementById("FCtotalPrice").setInnerHTML(format.format(priceFrete+totalWF).replaceAll("\\.", "\\,"));	
		totalPrice = priceFrete+totalWF;
	}
	
	public void onModuleLoad() {
		initCarShop();

		// navegacao
		String urlBase;
		String getPageAtual;
		getPageAtual = com.google.gwt.user.client.Window.Location
				.getParameter("page");

		urlBase = com.google.gwt.user.client.Window.Location.createUrlBuilder()
				.removeParameter("page").removeParameter("categoria_nome")
				.removeParameter("id_categoria").buildString();
		Anchor link0 = new Anchor("Home", urlBase);
		
		RootPanel.get("lk_home").add(link0);

		// urlBase =
		// com.google.gwt.user.client.Window.Location.createUrlBuilder()
		// .setParameter("page", "carrinho")
		// .removeParameter("categoria_nome")
		// .removeParameter("id_categoria").buildString();
		// Anchor link1 = new Anchor("Carrinho", urlBase);
		// RootPanel.get("lk_carrinho").add(link1);
		loginService.getAdminOn(new AsyncCallback<Administrador>() {
			
			@Override
			public void onSuccess(Administrador result) {
				if (result!=null) {
					System.out.println(result.getId());

					String urlBase = com.google.gwt.user.client.Window.Location
							.createUrlBuilder().setParameter("page", "addProduto")
							.removeParameter("categoria_nome")
							.removeParameter("id_categoria").buildString();
					Anchor link2 = new Anchor("Add Produtos", urlBase);
					RootPanel.get("lk_addProduto").add(link2);
				} 
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		// mudar if pra só exibir qdo ADM estiver logado
	
		
		// mudar if pra só exibir qdo cliente estiver logado
		if (true) {

			urlBase = com.google.gwt.user.client.Window.Location
					.createUrlBuilder().setParameter("page", "finalizarCompras")
					.removeParameter("categoria_nome")
					.removeParameter("id_categoria").buildString();
			Anchor link2 = new Anchor("Finalizar Compras", urlBase);
			RootPanel.get("lk_finalizarCompras").add(link2);
		}

		// fim navegacao

		// // inicio carrinho
		// if (getPageAtual != null)
		// if (getPageAtual.compareTo("carrinho") == 0) {
		//
		// String html = "";
		// final ScrollPanel s = new ScrollPanel();
		// final HTML ht = new HTML();
		// RootPanel.get("resultsContainer").add(s);
		//
		// String id_produto_string = com.google.gwt.user.client.Window.Location
		// .getParameter("id_produto");
		// int id_produto = 0;
		// if (id_produto_string != null) {
		// id_produto = Integer.parseInt(id_produto_string);
		//
		// // logica para adicionar produto ao carrinho com id_produto
		// }
		//
		// // html base lista de produtos
		//
		//
		// html +=
		// "<h4>Carrinho</h4><br/><br/><div style='width:80%;'><center><table style='border:1px solid rgb(148, 203, 50);' width='80%'>";
		//
		// html +=
		// "<tr style='background:rgb(148, 203, 50);color:#000;width:30px;border:1px solid #F0F0F0;'>";
		// html += "<td></td>";
		// html += "<td>Produto</td>";
		// html += "<td>Valor</td>";
		// html += "</tr>";
		//
		// int i = 1;
		// double precoTotal=0;
		// for (i = 1; i <= 3; i++) {
		//
		// html +=
		// "<tr  style='background:#FFFFFF;border:1px solid #F0F0F0 ;height:30px;'>";
		//
		// html += "<td  style='vertical-align: middle;'>"+i+"</td>";
		// html += "<td  style='vertical-align: middle;'>Musclo 4000</td>";
		// html += "<td style='vertical-align: middle;'>R$ 12,00</td>";
		//
		// html += "</tr>";
		//
		// precoTotal+=12.00;
		// }
		//
		// html += "</table></center>";
		// html +=
		// "<div class='brand-history'> <p>Total: R$ "+precoTotal+"0</p>"
		// + "<a href=''> Finalizar compra </a></div>";
		//
		// ht.setHTML(html);
		// s.add(ht);
		//
		// }
		// // fim carrinho

		// inicio single
		if (getPageAtual != null)
			if (getPageAtual.compareTo("single") == 0) {

				String detalhesHtml = "";
				final ScrollPanel s = new ScrollPanel();
				final HTML ht = new HTML();
				RootPanel.get("resultsContainer").add(s);

				String id_produto_string = com.google.gwt.user.client.Window.Location
						.getParameter("id_produto");
				String urlBaseCart;
				int id_produto = 0;
				if (id_produto_string != null) {
					id_produto = Integer.parseInt(id_produto_string);
					// recebeu um ID

					greetingService.byID(id_produto,
							new AsyncCallback<Produto>() {

								public void onFailure(Throwable caught) {
									// Show the RPC error message to the user
								}

								public void onSuccess(final Produto result) {
									String detalhesHtml = "";

									String urlBaseCart;
									// urlBaseCart =
									// com.google.gwt.user.client.Window.Location
									// .createUrlBuilder()
									// .setParameter("page", "carrinho")
									// .setParameter("id_produto",
									// id_produto_string)
									// .buildString();

									final ScrollPanel s = new ScrollPanel();
									final HTML ht = new HTML();

									Button btn = new Button(
											"Adicionar ao Carrinho");
									btn.getElement().setId("" + result.getId());
									btn.addClickHandler(new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											// putInCarShop(slotProd.get(Integer.parseInt(event
											// .getRelativeElement().getId())));
											putInCarShop(result);
											
										}

									});

									detalhesHtml += "<div class='details-page'>"
											+ "<div class='detalis-image'>"
											+ "<div id='content'> "
											+ "<img src='"
											+ result.getUrlImagemUnica()
											+ "'  width='200' height='250' title='' style='border: 1px solid #e5e5e5;'> "
											+ "</div>"
											+ "</div>"
											+ "<div class='detalis-image-details'>";

									String formatado;// =String.format("%.2f", result.getPreco());
									
//									formatado = "R$ "
//											+ formatado.replace(".", ",");
									
									
									formatado ="R$ "+ Double.toString(result.getPreco());

									detalhesHtml += "<div class='brand-value'>"
											+ "<h3>"
											+ result.getNome()
											+ "</h3>"
											+ "<div class='left-value-details'><ul><li>Pre&#231;o:</li><li>"
											+ "<span>&nbsp;</span></li><li><h5>"
											+ formatado
											+ "</h5></li><br />"
											+ "<li><p>&nbsp; </p></li></ul></div>";

									detalhesHtml += "<div class='right-value-details'>"
											+ "<a href='#'> &nbsp;</a><p>&nbsp;</p></div>"
											+ "<div class='clear'> </div> </div><div class='clear'> </div>";

									detalhesHtml += "<div class='brand-history' style='witdh:100%;float:left;'>"
											+ "<h3>Descri&#231;&#227;o: </h3>"
											+ "<p style='witdh:100%;'>"
											+ result.getDescricao()
											+ "</p><br/>";
									// + "<a href='"
									// //+ urlBaseCart
									// + "'>Adicionar ao carrinho</a></div>";

									detalhesHtml += "<div class='clear'> </div></div><div class='clear'> </div>";
									ht.setHTML(detalhesHtml);
									s.add(ht);
									// s.add();
									RootPanel.get("resultsContainer").add(s);
									RootPanel.get("btnComprar").add(btn);

								}

							});

				}

				ht.setHTML(detalhesHtml);
				s.add(ht);
				// closeButton.setFocus(true);

			}
		// fim single

		final Button sendButton = new Button("Buscar");
		final TextBox searchField = new TextBox();
		searchField.setText("");

		final ListBox searchOn = new ListBox();
		searchOn.addItem("Produto");
		searchOn.addItem("DescriÃ§Ã£o");
		final Label errorLabel = new Label();

		final Map<Integer, String> imgs_url = new HashMap<Integer, String>();

		final ListBox rangePrecos = new ListBox(); // Item Indexes
		rangePrecos.addItem("Todos"); // 0
		rangePrecos.addItem("Menor que R$10"); // 1
		rangePrecos.addItem("Entre R$10 e R$20"); // 2
		rangePrecos.addItem("Entre R$20 e R$40"); // 3
		rangePrecos.addItem("Entre R$40 e R$60"); // 4
		rangePrecos.addItem("Maior que R$60"); // 5
		// We can add style names to widgets
		// sendButton.addStyleName("sendButton");

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

		if (getPageAtual == null)
			if (searchField.getText().trim().isEmpty()) {

				Produto p = new Produto();
				// p.setNome(textToServer);

				String id_categoria_string = com.google.gwt.user.client.Window.Location
						.getParameter("id_categoria");

				int id_categoria = 0;

				if (id_categoria_string != null)
					id_categoria = Integer.parseInt(id_categoria_string);

				greetingService.todosProdutos(p, id_categoria,
						new AsyncCallback<ArrayList<Produto>>() {

							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							int qtd = 0;
							// NumberFormat nf = new
							// DecimalFormat("###,##0.00");
							String formatado;

							// DecimalFormat nf =new
							// DecimalFormat("###,##0.00");
							// java.text.DecimalFormat formato7 = new
							// java.text.DecimalFormat(".##");
							String categoria = com.google.gwt.user.client.Window.Location
									.getParameter("categoria_nome");

							public void onSuccess(ArrayList<Produto> result) {
								String html = "<h4>Categoria: " + categoria
										+ "</h4><div>";
								if (categoria == null)
									html = "<h4>Home</h4><div>";

								String urlBaseSingle;
								String urlBaseCart;

								for (Produto i : result) {

									urlBaseSingle = com.google.gwt.user.client.Window.Location
											.createUrlBuilder()
											.setParameter("page", "single")
											.setParameter("id_produto",
													Integer.toString(i.getId()))
											.buildString();

									urlBaseCart = com.google.gwt.user.client.Window.Location
											.createUrlBuilder()
											.setParameter("page", "carrinho")
											.setParameter("id_produto",
													Integer.toString(i.getId()))
											.buildString();
									urlBaseCart = urlBaseSingle;

									if (qtd % 4 == 0)
										html += "</div><div class='section group'>";

									String formatado ;
//									= "R$ "
//											+ String.format("%.2f", ""+i.getPreco())
//													.replace(".", ",");
									formatado ="R$ "+ Double.toString(i.getPreco());

									html += "<div class='grid_1_of_4 images_1_of_4 products-info'>";
									html += "<img src='"
											+ i.getUrlImagemUnica()
											+ "' width='100' height='150' >";
									html += "<a href='" + urlBaseSingle + "'>"
											+ i.getNome() + "</a>";
									html += "<h3>" + formatado + "</h3>";
									html += "<ul><li><a class='cart' href='"
											+ urlBaseCart + "'> </a></li>";
									html += "<li><a class='i' href='"
											+ urlBaseSingle
											+ "'> </a></li></ul></div>";

									qtd++;

									if (qtd == 16)
										break;

								}
								h.setHTML(html);
								r.add(h);
								closeButton.setFocus(true);
							}

						});

			}

		// listagem à direita de categorias
		final ScrollPanel s = new ScrollPanel();
		final HTML ht = new HTML();
		RootPanel.get("listaCategorias").add(s);

		SubCategoria p = new SubCategoria();
		greetingService.todasSubCategorias(p,
				new AsyncCallback<ArrayList<SubCategoria>>() {

					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					String urlBase;

					public void onSuccess(ArrayList<SubCategoria> result) {
						String html = "";

						for (SubCategoria i : result) {

							urlBase = com.google.gwt.user.client.Window.Location
									.createUrlBuilder()
									.removeParameter("page")
									.setParameter("categoria_nome",
											i.getCategoria())
									.setParameter("id_categoria",
											Integer.toString(i.getId()))
									.buildString();

							html += "<li><a href='" + urlBase + "'>"
									+ i.getCategoria() + "</a></li>";

						}
						ht.setHTML(html);
						s.add(ht);
						closeButton.setFocus(true);

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
			 * Send the name from the searchField to the server and wait for a
			 * response.
			 */
			private String seeCategory() {
				int i = searchOn.getSelectedIndex();

				switch (i) {
				case 0:
					return "nome";
				case 1:
					return "descricao";
				default:
					return "Error";
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
				if (FieldVerifier.containsSpecial(textToServer)) {
					errorLabel.setText("Procure sem caracteres especiais");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);

				Produto p = new Produto();
				p.setNome(textToServer);

				sendButton.setEnabled(true);

				// INSERIR INTERFACE DE BUSCA AQUI //
				// int total;
				greetingService.greetServer(p, seeCategory(), imgs_url,
						new AsyncCallback<ArrayList<Produto>>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							int qtd = 0;
							// NumberFormat nf = new
							// DecimalFormat("###,##0.00");
							String formatado;

							// DecimalFormat nf =new
							// DecimalFormat("###,##0.00");
							// java.text.DecimalFormat formato7 = new
							// java.text.DecimalFormat(".##");

							public void onSuccess(ArrayList<Produto> result) {
								String html = "<h4>Buscando por: "
										+ searchField.getText().trim()
										+ "</h4><div>";

								String urlBaseSingle;

								String urlBaseCart;

								for (Produto i : result) {
									// formatado = formato7.format(i.getNome());

									urlBaseSingle = com.google.gwt.user.client.Window.Location
											.createUrlBuilder()
											.setParameter("page", "single")
											.setParameter("id_produto",
													Integer.toString(i.getId()))
											.buildString();

									urlBaseCart = com.google.gwt.user.client.Window.Location
											.createUrlBuilder()
											.setParameter("page", "carrinho")
											.setParameter("id_produto",
													Integer.toString(i.getId()))
											.buildString();

									urlBaseCart = urlBaseSingle;

									if (qtd % 4 == 0)
										html += "</div><div class='section group'>";

									//formatado;
//									= "R$ "
//											+ Double.toString(""+i.getPreco())
//													.replace(".", ",");
									
//									formatado = "R$ "
//											+ String.format("%.2f", i.getPreco())
//													.replace(".", ",");
									
									formatado ="R$ "+ Double.toString(i.getPreco());

									switch (seeRangePrecos()) {
									case 0: {
										// html =
										// html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preÃ§o: "+Double.toString(i.getPreco())+"</td></tr>";
										// html = html
										// + "<tr><td><img src =\""
										// + i.getUrlImagemUnica()
										// +
										// "\" width=\"64\" height=\"64\"></td><td>"
										// / + i.getNome() + ", preÃ§o: "
										// + Double.toString(i.getPreco())
										// + "</td></tr>";

										html += "<div class='grid_1_of_4 images_1_of_4 products-info'>";
										html += "<img src='"
												+ i.getUrlImagemUnica()
												+ "' width='100' height='150' >";
										html += "<a href='" + urlBaseSingle
												+ "'>" + i.getNome() + "</a>";
										html += "<h3>" + formatado + "</h3>";
										html += "<ul><li><a class='cart' href='"
												+ urlBaseCart + "'> </a></li>";
										html += "<li><a class='i' href='"
												+ urlBaseSingle
												+ "'> </a></li></ul></div>";

										qtd++;
										break;
									}
									case 1: {
										if (i.getPreco() < 10)
											// html =
											// html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preÃ§o: "+Double.toString(i.getPreco())+"</td></tr>";

											// formatado =
											// nf.format(i.getPreco());

											html += "<div class='grid_1_of_4 images_1_of_4 products-info'>";
										html += "<img src='"
												+ i.getUrlImagemUnica()
												+ "' width='100' height='150' >";
										html += "<a href='" + urlBaseSingle
												+ "'>" + i.getNome() + "</a>";
										html += "<h3>" + formatado + "</h3>";
										html += "<ul><li><a class='cart' href='"
												+ urlBaseCart + "'> </a></li>";
										html += "<li><a class='i' href='"
												+ urlBaseSingle
												+ "'> </a></li></ul></div>";
										qtd++;
										break;
									}
									case 2: {
										if (i.getPreco() >= 10
												&& i.getPreco() < 20)
											// html =
											// html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preÃ§o: "+Double.toString(i.getPreco())+"</td></tr>";

											// formatado =
											// nf.format(i.getPreco());

											html += "<div class='grid_1_of_4 images_1_of_4 products-info'>";
										html += "<img src='"
												+ i.getUrlImagemUnica()
												+ "' width='100' height='150' >";
										html += "<a href='" + urlBaseSingle
												+ "'>" + i.getNome() + "</a>";
										html += "<h3>" + formatado + "</h3>";
										html += "<ul><li><a class='cart' href='"
												+ urlBaseCart + "'> </a></li>";
										html += "<li><a class='i' href='"
												+ urlBaseSingle
												+ "'> </a></li></ul></div>";
										qtd++;
										break;
									}
									case 3: {
										if (i.getPreco() >= 20
												&& i.getPreco() < 40)
											// html =
											// html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preÃ§o: "+Double.toString(i.getPreco())+"</td></tr>";
											// formatado =
											// nf.format(i.getPreco());

											html += "<div class='grid_1_of_4 images_1_of_4 products-info'>";
										html += "<img src='"
												+ i.getUrlImagemUnica()
												+ "' width='100' height='150' >";
										html += "<a href='" + urlBaseSingle
												+ "'>" + i.getNome() + "</a>";
										html += "<h3>" + formatado + "</h3>";
										html += "<ul><li><a class='cart' href='"
												+ urlBaseCart + "'> </a></li>";
										html += "<li><a class='i' href='"
												+ urlBaseSingle
												+ "'> </a></li></ul></div>";
										qtd++;
										break;
									}
									case 4: {
										if (i.getPreco() >= 40
												&& i.getPreco() < 60)
											// html =
											// html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preÃ§o: "+Double.toString(i.getPreco())+"</td></tr>";

											// formatado =
											// nf.format(i.getPreco());

											html += "<div class='grid_1_of_4 images_1_of_4 products-info'>";
										html += "<img src='"
												+ i.getUrlImagemUnica()
												+ "' width='100' height='150' >";
										html += "<a href='" + urlBaseSingle
												+ "'>" + i.getNome() + "</a>";
										html += "<h3>" + formatado + "</h3>";
										html += "<ul><li><a class='cart' href='"
												+ urlBaseCart + "'> </a></li>";
										html += "<li><a class='i' href='"
												+ urlBaseSingle
												+ "'> </a></li></ul></div>";
										qtd++;
										break;
									}
									case 5: {
										if (i.getPreco() >= 60)
											// html =
											// html+"<tr><td><img src =\""+imgs_url.get(i.getId())+"\" width=\"64\" height=\"64\"></td><td>"+i.getNome()+", preÃ§o: "+Double.toString(i.getPreco())+"</td></tr>";
											// formatado =
											// nf.format(i.getPreco());

											html += "<div class='grid_1_of_4 images_1_of_4 products-info'>";
										html += "<img src='"
												+ i.getUrlImagemUnica()
												+ "' width='100' height='150' >";
										html += "<a href='" + urlBaseSingle
												+ "'>" + i.getNome() + "</a>";
										html += "<h3>" + formatado + "</h3>";
										html += "<ul><li><a class='cart' href='"
												+ urlBaseCart + "'> </a></li>";
										html += "<li><a class='i' href='"
												+ urlBaseSingle
												+ "'> </a></li></ul></div>";

										qtd++;
										break;
									}
									}

								}
								// html = html + "</table>";
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

		final ListBox subCats = new ListBox(true);
		final TextBox productName = new TextBox();
		final TextBox productDescription = new TextBox();
		final TextBox productPrice = new TextBox();
		final TextBox productPicture = new TextBox();
		final TextBox productStorage = new TextBox();
		final Button sendProduct = new Button("Cadastrar");
		sendProduct.addStyleName("sendButton");

		getPageAtual = com.google.gwt.user.client.Window.Location
				.getParameter("page");
		// Na linha abaixo a div é ocultada
		// com.google.gwt.user.client.DOM.getElementById("exibiAddProduto")
		// .getStyle().setDisplay(Display.NONE);
		// colocar mias um IF se está logado como ADM
		// neste IF é definido se as opcoes de Criacao de Produtos Aparecerão
		if (getPageAtual != null){
			if (getPageAtual.compareTo("addProduto") == 0) {

				com.google.gwt.user.client.DOM
						.getElementById("exibiAddProduto").getStyle()
						.setDisplay(Display.BLOCK);
				RootPanel.get("productName").add(productName);
				RootPanel.get("productDescription").add(productDescription);
				RootPanel.get("productPrice").add(productPrice);
				RootPanel.get("productPicture").add(productPicture);
				RootPanel.get("productStorage").add(productStorage);
				RootPanel.get("productButton").add(sendProduct);

				subCatService
						.getSubCategorias(new AsyncCallback<ArrayList<HashMap<String, String>>>() {
							public void onFailure(Throwable caught) {
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							@Override
							public void onSuccess(
									ArrayList<HashMap<String, String>> result) {
								for (int i = 0; i < result.size(); i++) {
									HashMap<String, String> hashMap = result
											.get(i);
									subCats.addItem(hashMap.get("name") + " ("
											+ hashMap.get("catName") + ")",
											hashMap.get("id"));
								}
								RootPanel.get("subCats").add(subCats);
							}
						});

			}
		
			
			if (getPageAtual.compareTo("finalizarCompras") == 0) {

				loginService.getIdConnectedClient(new AsyncCallback<Integer>() {
					
					@Override
					public void onSuccess(Integer result) {
						if(result!=0){
						final int idClient = result;						
		
				
				com.google.gwt.user.client.DOM
						.getElementById("exibiFinalizarCompras").getStyle()
						.setDisplay(Display.BLOCK);
				
			//	finalizarCompraService.simularCompra(new AsyncCallback<Void>() {


				//	@Override
				//	public void onSuccess(Void result) {
						
						
						/**/
						
				com.google.gwt.user.client.DOM
				.getElementById("FCpayTreatement").getStyle()
				.setDisplay(Display.BLOCK);
				
					//	finalizarCompraService.getAtualCarrinho(new AsyncCallback<ArrayList<HashMap<String,String>>>() {
						carrinhoCompras.obterCarrinho(new AsyncCallback<List<CarrinhoComprasElemento>>() {
							@Override
							public void onSuccess(List<CarrinhoComprasElemento> result) {
							
							//	final ScrollPanel r = new ScrollPanel();
								final HTMLPanel h;// = new HTML();
								double totalWithoutFrete=0;
							final double totalWF;
								String html = "<table><tr><td>Nome</td><td>Pre&ccedil;o unitario</td><td>Quantidade</td><td>Pre&ccedil;o total</td></tr>";
							for(int i=0;i<result.size();i++){
								html += "<tr><td>"+result.get(i).getProduto().getNome()+"</td><td>"+result.get(i).getProduto().getPreco()+"</td><td>"+result.get(i).getQuantidade()+"</td><td>"+result.get(i).getProduto().getPreco()*result.get(i).getQuantidade()+"</td></tr>";
								totalWithoutFrete += result.get(i).getProduto().getPreco()*((double) result.get(i).getQuantidade());
							}
								totalWF=totalWithoutFrete;
								html +="<tr><td></td><td></td><td>Total sem frete: </td><td>"+Double.toString(totalWithoutFrete)+"</td></tr>";
								html+="<tr><td></td><td>Escolha da Frete</td><td id=\"FCrapido\"></td><td id=\"FCeco\"></td>";
								html +="<tr><td></td><td></td><td>Frete: </td><td id=\"FCfrete\"></td></tr>";
								html +="<tr><td></td><td></td><td>Total com Frete: </td><td id=\"FCtotalPrice\"></td></tr>";
									html	+= "</table>";
									h = new HTMLPanel(html);
								
								//h.setHTML(html);
								//r.add(h);
							

							 final RadioButton radio0 = new RadioButton("group0", "Rapido");
							  final RadioButton radio1 = new RadioButton("group0", "Economico");
							
								 final RadioButton cartao = new RadioButton("group1", "Cartao");
								  final RadioButton boleto = new RadioButton("group1", "Boleto");
							  
							  radio0.setEnabled(true);
							  h.add(radio0, "FCrapido");
							  h.add(radio1, "FCeco");
							  radio0.setValue(true);
							  RootPanel.get("FCrecupCarrinho").add(h);  
							finalizarCompraService.clienteId1(idClient, new AsyncCallback<HashMap<String,String>>() {
								
								@Override
								public void onSuccess(HashMap<String, String> result) {
									
									final TextBox sentName = new TextBox();
									RootPanel.get("FCsentName").add(sentName);
									sentName.setText(result.get("name"));
									
									final TextBox adress = new TextBox();
									RootPanel.get("FCadress").add(adress);
									adress.setText(result.get("adress"));
									
									
									
									final TextBox city = new TextBox();
									RootPanel.get("FCcity").add(city);
									city.setText(result.get("city"));
									
									final TextBox state = new TextBox();
									RootPanel.get("FCstate").add(state);
									state.setText(result.get("state"));
									
									final TextBox cep = new TextBox();
									RootPanel.get("FCcep").add(cep);
									cep.setText(result.get("cep"));
									
									final double frete = Double.parseDouble(result.get("frete"));
									
									h.getElementById("FCfrete").setInnerHTML(format.format(frete).replaceAll("\\.", "\\,"));
									h.getElementById("FCtotalPrice").setInnerHTML(format.format(frete+totalWF).replaceAll("\\.", "\\,"));
									
									totalPrice = frete+totalWF;
									city.addValueChangeHandler(new ValueChangeHandler<String>() {	
										@Override
										public void onValueChange(ValueChangeEvent<String> event) {	
											h.getElementById("FCfrete").setInnerHTML(format.format(frete).replaceAll("\\.", "\\,"));
											h.getElementById("FCtotalPrice").setInnerHTML(format.format(frete+totalWF).replaceAll("\\.", "\\,"));
										}
									});
									
									cep.addValueChangeHandler(new ValueChangeHandler<String>() {	
										@Override
										public void onValueChange(ValueChangeEvent<String> event) {
											h.getElementById("FCfrete").setInnerHTML(format.format(frete).replaceAll("\\.", "\\,"));
											h.getElementById("FCtotalPrice").setInnerHTML(format.format(frete+totalWF).replaceAll("\\.", "\\,"));
										}
									});
									
									adress.addValueChangeHandler(new ValueChangeHandler<String>() {	
										@Override
										public void onValueChange(ValueChangeEvent<String> event) {
											h.getElementById("FCfrete").setInnerHTML(format.format(frete).replaceAll("\\.", "\\,"));
											h.getElementById("FCtotalPrice").setInnerHTML(format.format(frete+totalWF).replaceAll("\\.", "\\,"));
										}
									});
									state.addValueChangeHandler(new ValueChangeHandler<String>() {	
										@Override
										public void onValueChange(ValueChangeEvent<String> event) {
											h.getElementById("FCfrete").setInnerHTML(format.format(frete).replaceAll("\\.", "\\,"));
											h.getElementById("FCtotalPrice").setInnerHTML(format.format(frete+totalWF).replaceAll("\\.", "\\,"));
										}
									});
									
									radio0.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											h.getElementById("FCfrete").setInnerHTML(format.format(frete).replaceAll("\\.", "\\,"));
											h.getElementById("FCtotalPrice").setInnerHTML(format.format(frete+totalWF).replaceAll("\\.", "\\,"));
										}
									});
									
									radio1.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											h.getElementById("FCfrete").setInnerHTML(format.format(frete).replaceAll("\\.", "\\,"));
											h.getElementById("FCtotalPrice").setInnerHTML(format.format(frete+totalWF).replaceAll("\\.", "\\,"));
										}
									});
									
									//sdgdfg;
									
									com.google.gwt.user.client.DOM
									.getElementById("FCcartao").getStyle()
									.setDisplay(Display.BLOCK);
									
									com.google.gwt.user.client.DOM
									.getElementById("FCboleto").getStyle()
									.setDisplay(Display.NONE);
									
									RootPanel.get("FCccartao").add(cartao);
									RootPanel.get("FCcboleto").add(boleto);
									cartao.setValue(true);
									
									
									final ListBox parcel = new ListBox(false);
									//dégueu
									
									parcel.addItem("1x", "1");
									parcel.addItem("2x", "2");
									parcel.addItem("3x", "3");
								 final RadioButton visa = new RadioButton("group3", "Visa");
								 final RadioButton masterCard = new RadioButton("group3", "MasterCard");
								 visa.setValue(true);
								 final TextBox nameCard = new TextBox();
								 final TextBox numCard = new TextBox();
								 final TextBox segCard = new TextBox();
								 ListBox monthCard = new ListBox(false);
								 ListBox yearCard = new ListBox(false);
								 
								 Button pagar = new Button(
											"Pagar");
								 
								 monthCard.addItem("1");
								 monthCard.addItem("2");
								 monthCard.addItem("3");
								 monthCard.addItem("4");
								 monthCard.addItem("5");
								 monthCard.addItem("6");
								 monthCard.addItem("7");
								 monthCard.addItem("8");
								 monthCard.addItem("9");
								 monthCard.addItem("10");
								 monthCard.addItem("11");
								 monthCard.addItem("12");
								 
								 yearCard.addItem("2014");
								 yearCard.addItem("2015");
								 yearCard.addItem("2016");
								 yearCard.addItem("2017");
								 yearCard.addItem("2018");
								 yearCard.addItem("2019");
								 yearCard.addItem("2020");
								 yearCard.addItem("2021");
								 
								 
								 RootPanel.get("FCkindCartao").add(visa);
								 RootPanel.get("FCkindCartao").add(masterCard); 
								 RootPanel.get("FCparcelamento").add(parcel);
								
								 RootPanel.get("FCcartaoNome").add(nameCard);
								 RootPanel.get("FCcartaoNumero").add(numCard);
								 RootPanel.get("FCcartaoSeguranca").add(segCard);
								 RootPanel.get("FCcartaoData").add(monthCard);
								 RootPanel.get("FCcartaoData").add(yearCard);
								 RootPanel.get("FCpagar").add(pagar);
									cartao.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											com.google.gwt.user.client.DOM
											.getElementById("FCcartao").getStyle()
											.setDisplay(Display.BLOCK);
									
											com.google.gwt.user.client.DOM
											.getElementById("FCboleto").getStyle()
											.setDisplay(Display.NONE);
										}
									});
									
									boleto.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											com.google.gwt.user.client.DOM
											.getElementById("FCcartao").getStyle()
											.setDisplay(Display.NONE);
											com.google.gwt.user.client.DOM
											.getElementById("FCboleto").getStyle()
											.setDisplay(Display.BLOCK);
										}
									});
									
									pagar.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											// TODO Auto-generated method stub
											if(cartao.getValue() &&( city.getText().length()== 0 || 
													cep.getText().length() == 0 || 
													adress.getText().length()==0 ||
													state.getText().length()==0 ||
													nameCard.getText().length() ==0 ||
													numCard.getText().length() == 0 ||
													segCard.getText().length() == 0
													)){
												
												Window.alert("O formulario não foi bem preenchido");
											} else {
											if(cartao.getValue()){
											if(Window.confirm("Pagamento de "+format.format(totalPrice).replaceAll("\\.", "\\,")+" com parcelamento "
													+ "de "+parcel.getValue(parcel.getSelectedIndex())+"x"
															+ " "+format.format(totalPrice/Double.parseDouble(parcel.getValue(parcel.getSelectedIndex()))).replaceAll("\\.", "\\,")
															+". Voce confirma ?" )){
												RootPanel.get("FCpayMsg").clear();
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setDisplay(Display.BLOCK);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayMsg").getStyle()
												.setDisplay(Display.BLOCK);
												
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setZIndex(1);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setBottom(0, Unit.PX);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setTop(0, Unit.PX);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setLeft(0, Unit.PX);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setRight(0, Unit.PX);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setBackgroundColor("#000000");
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setOpacity(0.50);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setPosition(Position.FIXED);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setMargin(0.0, Unit.PX);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setWidth(100.0, Unit.PCT);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayTreatement").getStyle()
												.setHeight(100.0, Unit.PCT);
											
												
												com.google.gwt.user.client.DOM
												.getElementById("FCpayMsg").getStyle()
												.setColor("#FFFFFF");
												
												com.google.gwt.user.client.DOM
												.getElementById("FCpayMsg").getStyle()
												.setZIndex(2);
												com.google.gwt.user.client.DOM
												.getElementById("FCpayMsg").getStyle()
												.setBackgroundColor("#000000");
//#94CB42
												com.google.gwt.user.client.DOM
												.getElementById("FCpayMsg").getStyle()
												.setOpacity(1.00);

												com.google.gwt.user.client.DOM
												.getElementById("FCpayMsg").getStyle()
												.setMarginLeft(37, Unit.PCT);
												
												com.google.gwt.user.client.DOM
												.getElementById("FCpayMsg").getStyle()
												.setMarginRight(37, Unit.PCT);
												
												com.google.gwt.user.client.DOM
												.getElementById("FCpayMsg").getStyle()
												.setPadding(10, Unit.PX);
												
												
												com.google.gwt.user.client.DOM
												.getElementById("FCpayMsg").getStyle()
												.setPosition(Position.FIXED);												
												
												RootPanel.get("FCpayMsg").add(new HTMLPanel("Aguardando o pagamento"));
											Timer t = new Timer() {
												
												@Override
												public void run() {
													Button buttonOk = new Button("ok");
													
													
													if(Integer.parseInt(segCard.getText())%5==0){
														com.google.gwt.user.client.DOM
														.getElementById("FCpayMsg").getStyle()
														.setColor("#FF0000");
														RootPanel.get("FCpayMsg").clear();
														RootPanel.get("FCpayMsg").add(new HTMLPanel("O pagamento NAO foi efetuado. <br/>")); 												
														RootPanel.get("FCpayMsg").add(buttonOk);
														buttonOk.addClickHandler(new ClickHandler() {
															
															@Override
															public void onClick(ClickEvent event) {
																// TODO Auto-generated method stub
																com.google.gwt.user.client.DOM
																.getElementById("FCpayMsg").getStyle()
																.setDisplay(Display.NONE);
																com.google.gwt.user.client.DOM
																.getElementById("FCpayTreatement").getStyle()
																.setDisplay(Display.NONE);
															}
														});
													
													} else {
														
														com.google.gwt.user.client.DOM
														.getElementById("FCpayMsg").getStyle()
														.setColor("#94CB42");
														RootPanel.get("FCpayMsg").clear();
														RootPanel.get("FCpayMsg").add(new HTMLPanel("O pagamento foi efetuado com sucesso. <br/> Obrigado para ter escolhido NutriNet. <br />")); 												
														RootPanel.get("FCpayMsg").add(buttonOk);
														buttonOk.addClickHandler(new ClickHandler() {
															
															@Override
															public void onClick(ClickEvent event) {
																// TODO Auto-generated method stub
																com.google.gwt.user.client.DOM
																.getElementById("FCpayMsg").getStyle()
																.setDisplay(Display.NONE);
																com.google.gwt.user.client.DOM
																.getElementById("FCpayTreatement").getStyle()
																.setDisplay(Display.NONE);
																carrinhoCompras.esvaziarCarrinho(new AsyncCallback<Boolean>() {

																	@Override
																	public void onFailure(
																			Throwable caught) {
																		// TODO Auto-generated method stub
																		
																	}

																	@Override
																	public void onSuccess(
																			Boolean result) {
																		// TODO Auto-generated method stub
																		
																	}
																});
																Window.Location.replace(com.google.gwt.user.client.Window.Location.createUrlBuilder()
																		.removeParameter("page").removeParameter("categoria_nome")
																		.removeParameter("id_categoria").removeParameter("id_produto").buildString());
															}
														});
													
													}	
													
													
													
											
													
													
												}
											};
											
											t.schedule(5000);
												
											}	
											} else {
												
												
												if(Window.confirm("Pagamento de "+format.format(totalPrice).replaceAll("\\.", "\\,")+" com boleto, voce tem 3 dias para paga-lo. Voce confrima ?")){
												Window.open("boleto/boleto.png", "_blank", null);


												Window.Location.replace(com.google.gwt.user.client.Window.Location.createUrlBuilder()
														.removeParameter("page").removeParameter("categoria_nome")
														.removeParameter("id_categoria").removeParameter("id_produto").buildString());
												
												carrinhoCompras.esvaziarCarrinho(new AsyncCallback<Boolean>() {

													@Override
													public void onFailure(
															Throwable caught) {
														// TODO Auto-generated method stub
														
													}

													@Override
													public void onSuccess(
															Boolean result) {
														// TODO Auto-generated method stub
														
													}
												});
												
												}
												
												}
											
											}
											
										}
									});
								}
								
								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									
								}
							});
							
							
							
							
							
							
							}
							
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
							}
						});
						
						
						/**/
				//	}
			//	});
			
				subCatService
						.getSubCategorias(new AsyncCallback<ArrayList<HashMap<String, String>>>() {
							public void onFailure(Throwable caught) {
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							@Override
							public void onSuccess(
									ArrayList<HashMap<String, String>> result) {
								for (int i = 0; i < result.size(); i++) {
									HashMap<String, String> hashMap = result
											.get(i);
									subCats.addItem(hashMap.get("name") + " ("
											+ hashMap.get("catName") + ")",
											hashMap.get("id"));
								}
								RootPanel.get("subCats").add(subCats);
							}
						});
				
				}
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});

			}
			
			//AQUI
		
	}

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

				Produto p = new Produto();
				Administrador admin = new Administrador();
				// modificar quando tivermos administradores
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
				for (int i = 0; i < subCats.getItemCount(); i++) {
					if (subCats.isItemSelected(i)) {
						SubCategoria subCatObj = new SubCategoria();
						subCatObj.setId(Integer.valueOf(subCats.getValue(i)));
						subCatsAL.add(subCatObj);
					}
				}

				p.setSubCat(subCatsAL);

				sendButton.setEnabled(true);

				// INSERIR INTERFACE DE BUSCA AQUI //
				inserirProdutoService.inserirProdutoServer(p,
						new AsyncCallback<Boolean>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(Boolean result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result.toString());
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}

		}

		HandlerCadastrarProduto handlerCadastrarProduto = new HandlerCadastrarProduto();
		sendProduct.addClickHandler(handlerCadastrarProduto);

		// parte login
		final TextBox loginUsername = new TextBox();
		RootPanel.get("loginUsername").add(loginUsername);
		loginUsername.setText("");
		final PasswordTextBox loginSenha = new PasswordTextBox();
		RootPanel.get("loginSenha").add(loginSenha);
		loginSenha.setText("");

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
				loginService.loginCliente(loginUsername.getText(),
						loginSenha.getText(), new AsyncCallback<Boolean>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(Boolean ok) {
								dialogBox.setText("Login:");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								if(ok){
								serverResponseLabel
										.setHTML("Voce esta logado no sistema!");
								}
								else {
									serverResponseLabel
									.setHTML("Senha ou Usuario invalidos!");
								}
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}
		HandlerLogin handlerLogin = new HandlerLogin();
		loginButton.addClickHandler(handlerLogin);
	}

	/**
	 * Metodo para iniciar a List<Map> do carrinho.
	 * */
	private void initCarShop(){
		carrinhoCompras.initCarShop(new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Erro ao iniciar o Carrinho de compras");
				
			}

			@Override
			public void onSuccess(Void result) {
				carrinhoCompras.obterCarrinho(new AsyncCallback<List<CarrinhoComprasElemento>>() {
					
					@Override
					public void onSuccess(List<CarrinhoComprasElemento> result) {
						int size = 0;
						for(CarrinhoComprasElemento i : result){
							size = size + i.getQuantidade();
						}
						mountShopCarStatus(size);
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Erro ao obter o carrinho de compras");
						
					}
				});
				
			}
			
		});
	}
	
	/**
	 * Metodo para montar o status do carrinho ao topo direito da pagina
	 * */
	private void mountShopCarStatus(Integer quant) {
		RootPanel car = RootPanel.get("shopCar");
		final Button carBtn = new Button("Ver carrinho");
		
		carBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mountShopCarContainer();
			}
		});
		
		car.clear();
		car.add(new HTML("Carrinho de Compras("
				+ quant.toString() + ")"));
		car.add(carBtn);


	}

	/** Quantidade default de Produtos no carrinho */

	/**
	 * Metodo para exibir os produtos no carrinho
	 * */
	private void mountShopCarContainer() {
		carrinhoCompras.obterCarrinho(new AsyncCallback<List<CarrinhoComprasElemento>>() {
			
			@Override
			public void onSuccess(final List<CarrinhoComprasElemento> result) {
				RootPanel car = RootPanel.get("shopCar");
				final Button carBtn = new Button("Fechar Carrinho");
				
				carBtn.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						initCarShop();
					}
				});
				
				car.clear();
				car.add(carBtn);
				
				buildCarShopTable(car, result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Erro ao contruir carrinho container");
				
			}
		});
	}

	/**
	 * Adiciona produto no carrinho
	 * */
	private void putInCarShop(Produto produto) {

		carrinhoCompras.adicionarProduto(produto, new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Erro inserir carrinho");
			}

			@Override
			public void onSuccess(Integer result) {
				mountShopCarStatus(result);
			}

		});

	}

	private final CarrinhoComprasAsync carrinhoCompras = GWT
			.create(CarrinhoCompras.class);

	/**
	 * monta a tabela com a lista de produtos no carrinho
	 * */
	private void buildCarShopTable(RootPanel rp,
			final List<CarrinhoComprasElemento> list) {

		final FlexTable table = new FlexTable();
		int count = 1;

		table.setText(0, 0, "Quantidade");
		table.setText(0, 1, "Produto");

		for (Integer i = 0; i < list.size(); i++) {
			final Produto prod = list.get(i).getProduto();
			Button btn = new Button("Apagar");

			btn.getElement().setId(i.toString());
			btn.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					carrinhoCompras.removerProduto((list.get(Integer
							.parseInt(event.getRelativeElement().getId()))
							.getProduto()), new AsyncCallback<Integer>() {

						@Override
						public void onSuccess(Integer result) {

						}

						@Override
						public void onFailure(Throwable caught) {
							System.out.println("Erro ao remover produto");
						}
					});

					int row = table.getCellForEvent(event).getRowIndex();
					table.removeRow(row);
				}

			});

			table.setText(count, 0, list.get(i).getQuantidade().toString());
			table.setHTML(count, 1, "<img src =\"" + prod.getUrlImagemUnica()
					+ "\" width=\"64\" height=\"64\">");
			table.setText(
					count,
					2,
					prod.getNome() + ", preço: "
							+ Double.toString(prod.getPreco()));
			table.setWidget(count, 3, btn);

			count++;
		}
		rp.add(table);


	}

	
}
