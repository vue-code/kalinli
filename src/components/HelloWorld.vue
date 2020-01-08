<template>
  <v-app id="inspire">
    <v-content>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col
            cols="12"
            sm="8"
            md="4"
          >
            <v-card class="elevation-12">
              <v-toolbar
                color="primary"
                dark
                flat
              >
                <v-toolbar-title>Login form</v-toolbar-title>
                <v-spacer />
                <v-tooltip bottom>
                  <template v-slot:activator="{ on }">
                    <v-btn
                      :href="source"
                      icon
                      large
                      target="_blank"
                      v-on="on"
                    >
                      <v-icon>mdi-code-tags</v-icon>
                    </v-btn>
                  </template>
                  <span>Source</span>
                </v-tooltip>
                <v-tooltip right>
                  <template v-slot:activator="{ on }">
                    <v-btn color="primary" v-on:click="request">Request</v-btn>
                  </template>
                  <span>Codepen</span>
                </v-tooltip>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    v-model="username"
                    label="Username"
                    name="login"
                    type="text"
                  />

                  <v-text-field
                    v-model="password"
                    id="password"
                    label="Password"
                    name="password"
                    type="password"
                  />
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <v-btn color="primary" v-on:click="login">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>

      </v-container>
    </v-content>
  </v-app>
</template>
<script lang="ts">
import Vue from 'vue'
import Vuetify from 'vuetify';
import  axios from 'axios'
export default Vue.extend({
    name: 'hello',
    vuetify: new Vuetify(),
    data: () => ({
        username: '',
        password: ''

    }),
    methods:{
        
      login:function() {
        var bodyFormData = new FormData();

        bodyFormData.set('username',this.username);
        bodyFormData.set('password',this.password);
        axios({
          method:'post',
          url:'http://localhost:8080/login',
          data:bodyFormData,
          headers:{'Content-Type': 'multipart/form-data'
          }
        })
        .then(function(){
          alert("Login Success")
          
        })
        .then(function(error){
          console.log(error)
        })
        
      },
      request:function() {
        axios({
          method:'post',
          url:'http://localhost:8080/user/hello',
          headers:{'Content-Type': 'multipart/form-data'
          }
          
        })
        .then(function(){
          alert("Request Success")
        })
        .then(function(error){
          console.log(error)
        })


      }
    }  
})
</script>

