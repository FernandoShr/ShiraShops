# ShiraShops

Neste projeto desenvolvi uma aplicação Ecommerce (Da qual denominei Shira Shop) em microsserviços utilizando Springboot Java.
Seu objetivo é simular o fluxo de um Ecommerce desde a criação do usuário, cadastro e busca de produtos e a realização do pedido.

## Sobre o Projeto:
A cada novo serviço feito, fui alterando e aperfeiçoando diferentes formas de implementações. Ao todo foram desenvolvidas estas APIs (nesta ordem):

* EurekaServer
* GatewayApi
* UserApi
* ProductApi
* PedidoApi
* AuthenticationApi
* DiscountApi

Inicialmente, a arquitetura dos projetos foi desenvolvida por **MVC** e na DiscountApi apliquei a **Arquitetura Limpa** tornando-a mais escalável à possibilidade de adição de novos serviços.

Além disso, todas as APIs estão conectadas à GatewayApi, a qual está conectada no Servidor Eureka. Desse modo, é possível acessar todas as APIs através de uma única porta, a do Gateway, precisando alterar apenas o caminho da URL para obter a ação desejada.

**Banco de Dados utilizado:** MySQL 
