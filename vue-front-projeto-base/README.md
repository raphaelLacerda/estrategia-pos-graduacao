# Vue.Js

## Instalação

```
yarn global add @vue/cli
vue --version
yarn global add @vue/cli @vue/cli-service-global
```

## Hello word

Vamos criar o App.vue

```
vue serve -o
```

## Scaffolding

```
vue create cgu
vue add router
npm run serve
```

Pode usar interface gráfica importando a pasta

```
vue ui
```

## Modificando a página about e hello

- modificar o div="app"
- mostrar o code splitting no browser

## Criando a página de Aluno

Adicionando o bootstrap

```
yarn add vue bootstrap bootstrap-vue-3
# doesnt support Vue3 yet
yarn add bootstrap-vue
```

Alterar o main.js para importar o BootStrap

```
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-vue/dist/bootstrap-vue.css"
```

- Criando o Aluno.vue
- Criando form com bootstrap
- definindo callbacks
- listagem de alunos

## Salvando na API rest

Instalando AXIOS
