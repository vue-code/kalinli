<template>
<div id="app">
  <v-app id="inspire">
    <v-form>
      <v-container >
        <v-row >
          <v-col cols="12" sm="4" md="3">
            <v-text-field
              v-model.number="a"
              outlined
              dense
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="4" md="1">
            <v-data><center>+</center></v-data>
          </v-col>
          <v-col cols="12" sm="4" md="3">
            <v-text-field
              v-model.number="b"
              outlined
              dense
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="4" md="1">
            <v-data><center>=</center></v-data>
          </v-col>
          <v-col cols="12" sm="4" md="3">
            <v-text-field
              v-model.number="c"
              outlined
              dense
            ></v-text-field>
          </v-col>
        </v-row>
        <div class="text-center">
            <v-btn large color="primary" v-on:click="add">
              <span>Add</span>
              <v-icon>mdi-heart</v-icon>
            </v-btn>
        </div>
        <v-row>
        </v-row>
      </v-container>
    </v-form>
  </v-app>
</div>
</template>

<script lang="ts">
import Vue from 'vue';
import Vuetify from 'vuetify'

export default Vue.extend({
  name: 'HelloWorld',
   vuetify: new Vuetify(),

  data: () => ({
      a: '',
      b: '',
      c: '',
  }),
  
  methods:{
      add: function() {
          this.c= this.a + this.b
      }
  }
});
</script>
