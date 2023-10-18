<template>
  <div class="container">
    <form method="post">
      <div class="mb-3 row">
        <label class="col-sm-2 col-form-label">Nome:</label>
        <div class="col-sm-10">
          <input
            name="nome"
            v-model="aluno.nome"
            type="text"
            class="form-control"
          />
        </div>
      </div>
      <div class="mb-3 row">
        <label class="col-sm-2 col-form-label">Email:</label>
        <div class="col-sm-10">
          <input
            name="email"
            v-model="aluno.email"
            type="email"
            class="form-control"
          />
        </div>
      </div>
      <div class="col-auto">
        <button
          type="submit"
          @click.prevent="save"
          class="btn btn-lg btn-outline-primary mb-3"
        >
          Salvar
        </button>
      </div>
    </form>
    <div id="listaAlunos" v-if="alunos.length > 0" class="row mt-2">
      <h3>Listagem de Alunos {{ alunos.length }}</h3>
      <ul>
        <li class="mt-3" v-for="(aluno, index) in alunos" :key="index">
          {{ aluno.nome }} - {{ aluno.email }} -
          <button
            @click="edit(aluno)"
            class="btn btn-sm btn-outline-secondary me-3"
          >
            editar
          </button>
          <button class="btn btn-sm btn-outline-danger" @click="remove(aluno)">
            remover
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/v1/alunos';
export default {
  name: 'Aluno',
  data() {
    return {
      alunos: [],
      aluno: {},
    };
  },
  methods: {
    save: function () {
      console.log('salvando aluno ' + JSON.stringify(this.aluno));

      if (this.aluno.id) {
        axios
          .put(`${API_URL}/${this.aluno.id}`, this.aluno)
          .then(() => {
            console.log('atualizado com sucesso');
          });
      } else {
        axios.post(API_URL, this.aluno).then((response) => {
          this.alunos.push(response.data);
        });
      }

      this.aluno = {};
    },
    edit: function (aluno) {
      console.log('editando aluno ' + JSON.stringify(this.aluno));
      this.aluno = aluno;
    },
    remove: function (aluno) {
      this.alunos = this.alunos.filter((a) => a.id !== aluno.id);
      axios.delete(`${API_URL}/${aluno.id}`).then(() => {
        console.log('removido com sucesso');
      });
    },
  },
  beforeCreate() {
    console.log('antes de criar');
  },
  created() {
    console.log('foi criado');
  },
  beforeUpdate() {
    console.log('antes de atualizar');
  },
  updated() {
    console.log('foi atualizado');
  },
  beforeMount() {
    console.log('antes de montar');
  },
  mounted() {
    console.log('montado');
    axios.get(API_URL).then((response) => {
      this.alunos = response.data;
    });
  },
  //está depreciado
  // beforeDestroy() {
  //   console.log('antes de destroir');
  // },
  //está depreciado
  // destroyed() {
  //   console.log('destroído');
  // },
  beforeUnmount() {
    console.log('antes de desmontar');
  },
  unmounted() {
    console.log('desmontando');
  },
};
</script>
