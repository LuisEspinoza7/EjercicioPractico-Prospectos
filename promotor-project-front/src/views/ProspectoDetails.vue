<template>
    <v-container fluid>
        <div class="mt-n3 font-weight-bold overline">
            <v-breadcrumbs large :items="itemsNav" divider=">"></v-breadcrumbs>
        </div>
        <v-card class="ma-auto pb-4" width=94%>
            <v-row class="mt-2">
                <v-col md="3">
                    <img class="ml-8" src="../assets/userlogo.png" style="width:220px;">
                </v-col>
                <v-col class="ml-2" md="8">
                    <span class="mb-5 font-weight-light display-1 ">Datos Prospecto - {{this.prospecto.estatus}}</span>
                    <v-row class="mt-4">
                        <v-text-field outlined readonly class="pl-4 subtitle-1" v-model=this.prospecto.nombre label="Nombre"/>
                        <v-text-field outlined readonly class="pl-4 subtitle-1" v-model=this.prospecto.apellidopat label="Apellido Paterno"/>
                        <v-text-field outlined readonly class="pl-4 subtitle-1" v-model=this.prospecto.apellidomat label="Apellido Materno"/>
                    </v-row>
                    <v-row>
                        <v-text-field outlined readonly class="pl-4 subtitle-1" v-model=this.prospecto.calle label="Calle"/>
                        <v-text-field outlined readonly class="pl-4 subtitle-1" v-model=this.prospecto.numero label="Número"/>
                        <v-text-field outlined readonly class="pl-4 subtitle-1" v-model=this.prospecto.colonia label="Colonia"/>
                    </v-row>
                    <v-row >
                        <v-text-field outlined readonly class="pl-4  subtitle-1" v-model=this.prospecto.cp label="Codigo Postal"/>
                        <v-text-field outlined readonly class="pl-4 subtitle-1" v-model=this.prospecto.telefono label="Teléfono"/>
                        <v-text-field outlined readonly class="pl-4 subtitle-1" v-model=this.prospecto.rfc label="RFC"/>
                    </v-row>
                    <v-row class="mb-n4"
                    v-if="estatusEnviado">
                        <v-spacer></v-spacer>
                        <v-spacer></v-spacer>
                        <span class="pt-4 pr-4 title font-weight-bold">Seleccionar estatus</span>
                    <v-select 
                    item-color="teal"
                    outlined
                    class="title font-weight-light"
                    style="width:74px" 
                    v-model="select"
                    :items="estatus" 
                    item-text="status"
                    :error-messages="selectErrors"
                    @change="selectEstatus"
                    @blur="$v.select.$touch()"
                    required
                    persistent-hint
                    return-object
                    label="Estatus"></v-select>
                    </v-row>
                </v-col>
            </v-row>
            <v-divider class="teal mx-4 pb-1 my-2"></v-divider>
            <div v-if="esRechazado"> 
                <span class="px-4 font-weight-light headline">Observaciones</span>
                <form @submit.prevent="submit">
                <v-textarea label="Observacion" v-model="prospecto.observaciones"
                :error-messages="observacionError" :counter="100" @input="$v.prospecto.observaciones.$touch()"
                @blur="$v.prospecto.observaciones.$touch()" required
                :readonly="this.deshabilitarObs" 
                class="mt-4 px-4 title font-italic font-weight-light" 
                outlined  />
                </form>
            </div>
            <v-row>
                        <!-- Aqui van los cargados -->
                    <v-col md="5" class="font-weight-light title ml-8" >
                    <v-row>
                        <span class="ml-2 mb-2 font-weight-light headline">Documentos agregados</span>
                    </v-row>
                    <v-divider class="ml-n2 mr-n12 mb-2 teal"></v-divider>
                                <v-row v-for="(documento, index) in documentos" :key="index">
                                <v-icon  big class="mr-2 ml-2"> mdi-file-check</v-icon>
                                - <span class="ml-2 font-italic">
                                    {{documento.nombredoc}}
                                  </span>
                                 <v-spacer></v-spacer>
                                </v-row>
                        </v-col>
                    </v-row>
            <v-row>
            <v-spacer></v-spacer>
            <v-btn v-if="estatusEnviado" @click="exit" class="error font-weight-light subtitle-1 mr-2">Salir</v-btn>
            <v-btn v-if="estatusEnviado" @click="submit" class="primary font-weight-light subtitle-1 mr-7">Enviar</v-btn>
            </v-row>
             
        </v-card>
        <v-row class="mt-2">
        <v-spacer></v-spacer>
        <v-alert
        v-if="exitAlert"
        width=64%
        prominent
        type="warning">
            <v-row align="center">
                <v-col class="font-weight-light title" md="8">¿Seguro que desea salir? No se guardará un nuevo estatus. </v-col>
                <v-col md="4">
                   <v-row>
                       <v-spacer></v-spacer>
                       <v-btn @click="exitConfirm" class=" mr-2">Salir</v-btn>
                       <v-btn @click="closeAlert" class="mr-4">Cancelar</v-btn>
                   </v-row>
                </v-col>
             </v-row>
        </v-alert>
        <v-spacer></v-spacer>
        </v-row>
    </v-container>
</template>

<script>
import { required, maxLength, } from 'vuelidate/lib/validators'
import ProspectoService from '../services/ProspectoService'
import DocumentoService from '../services/DocumentoService'
export default {
    data(){
        return {
            idprospecto:null,
            prospecto:{
                observaciones: '',
            },
            documentos:[],
            estatusEnviado: false,
            itemsNav: [ {
            text: 'Home',
            disabled: false,
            href: '/'
            },
            {
            text: 'Gestionar Prospectos',
            disabled: false,
            href: '/listaprospectos'
            },
            {
            text: 'Prospecto',
            disabled: true,
            href: '/prospectodetails'
            }
        ],
        select: null,
        estatus:[
            {status: 'Aceptado'},
            {status:'Rechazado'}
        ],
        esRechazado: false,
        deshabilitarObs: false,
        exitAlert: false,
        }
    },
    prospectoService : null,
    documentoService: null,
    validations:{
        prospecto:{
            observaciones: {required, maxLength: maxLength(100)},
        },
        select: {required}
    },
    computed: {
        observacionError(){
            const errors = []
            if(!this.$v.prospecto.observaciones.$dirty) return errors
            !this.$v.prospecto.observaciones.required && errors.push('Debe indicar la observacion')
            !this.$v.prospecto.observaciones.maxLength && errors.push('Máximo 100 caracteres')
            return errors
        },
        selectErrors () {
        const errors = []
         if (!this.$v.select.$dirty) return errors
        !this.$v.select.required && errors.push('Debe seleccionar estatus')
         return errors
        }
    },
    created() {
        this.idprospecto= this.$route.params.idprospecto
        this.prospectoService = new ProspectoService()
        this.documentoService = new DocumentoService()
        this.getProspecto()
        this.getDocument()
    },
    updated() {
        if(this.prospecto.estatus == 'Enviado'){
            this.estatusEnviado = true
       }else{
           if(this.prospecto.estatus =='Rechazado'){
               this.esRechazado = true
               this.deshabilitarObs = true
           }
       }
    },
    methods:{
        getProspecto(){
            this.prospectoService.getProspecto(this.idprospecto).then(data=>{
                this.prospecto=data.data
            })
        },
        getDocument(){
             this.documentoService.getAll().then(data=>{
                 data.data.forEach(element => {
                     if(element.idprospecto==this.idprospecto){
                         this.documentos.push(element)
                     }
                 });
            })
        },
        selectEstatus(){
            if(this.select.status == 'Rechazado'){
                this.esRechazado = true
            }else{
                this.esRechazado = false
                this.$v.$reset()
            }
        },
        exit(){
            if(this.estatusEnviado){
            this.exitAlert = true
            }else
            this.exitConfirm()
        },
        exitConfirm(){
            this.$router.push({ name: 'listaprospectos'})
        },
        closeAlert(){
            this.exitAlert=!this.exitAlert
        },
        submit(){
            this.$v.$touch()

            if(!(this.$v.$invalid && this.esRechazado || this.select== null)){
                this.prospecto.estatus = this.select.status
                this.prospectoService.save(this.prospecto).then(data => {
                if(data.status === 200){
                    this.prospecto={
                        observaciones: null
                    }
                    this.$v.$reset()
                    this.getProspecto()
                    this.estatusEnviado = false
                    alert('Prospecto actualizado')
                }
            })
            }
        }
    }
}
</script>