# DesafioSicredi

## Bibliotecas utilizadas
- Retrofit, para realizar requisições à API
- Picasso, para carregar as imagens dos eventos
- Material, para o layout de card na RecyclerView e inputs de texto
- Koin, para a injeção de Dependências (ViewModel do EventsFragment e instância do Retrofit para comunicação com a API)

## Breve explicação do projeto
- DesafioSicredi, Application padrão do app, responsável por carregar os módulos
- MainActivity, tem como responsabilidade carregar o EventsFragment
- EventsFragment, lista os eventos obtidos da API
- EventsViewModel, faz a requisição para a API e comunica, através de LiveData, para o EventsFragment, as atualizações das requisições
- EventsAdapter, responsável por controlar o layout e ações de cada item da RecyclerView de Events
