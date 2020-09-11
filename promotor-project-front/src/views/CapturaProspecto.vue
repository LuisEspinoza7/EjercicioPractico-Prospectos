<template>
    <v-container fluid>
        <div class="mt-n4 font-weight-bold overline">
            <v-breadcrumbs large :items="itemsNav" divider=">"></v-breadcrumbs>
        </div>
        <div class="mt-n3">
        <span class="ml-4 display-1 font-weight-light" >Capturar nuevo prospecto</span>
        </div>
                <v-row>
        <v-spacer></v-spacer>
         <v-alert v-if="type" width=54% prominent type="success" class="font-weight-light title mt-3">
            Se guardó la captura correctamente.
        </v-alert>
        <v-alert v-if="error" width=54% prominent type="error" class="font-weight-light title mt-3">
            Se han encontrado errores en el formulario, favor de llenar adecuadamente
        </v-alert>
        <v-alert
        class="mt-4"
        v-if="exitAlert"
        width=54%
        height=10%
        prominent
        type="warning">
            <v-row align="center">
                <v-col class="font-weight-light title" md="8">Se perderá toda la captura, seguro que desea salir?</v-col>
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
        <v-card class=" mt-4">
            <v-card-title class="headline font-weight-light">
                Ingresar datos
            </v-card-title>
            <v-card-text >
                <form @submit.prevent="submit">
                    <v-row>
                        <!-- //Nombre -->
                        <v-text-field label="Nombre" v-model.trim="prospecto.nombre"
                        :error-messages="nombreError" :counter="20" @input="$v.prospecto.nombre.$touch()"
                        @blur="$v.prospecto.nombre.$touch()" required class="pl-4 pr-7" />
                        <!-- ApellidoPaterno -->
                        <v-text-field label="Apellido Paterno" v-model="prospecto.apellidopat"
                        :error-messages="apellidopatError" :counter="20" @input="$v.prospecto.apellidopat.$touch()"
                        @blur="$v.prospecto.apellidopat.$touch()" required class="pr-7" />
                        <!-- ApellidoMaterno -->
                        <v-text-field label="Apellido Materno" v-model="prospecto.apellidomat"
                        :error-messages="apellidomatError" :counter="20" @input="$v.prospecto.apellidomat.$touch()"
                        @blur="$v.prospecto.apellidomat.$touch()" class="pr-7" />
                    </v-row>
                    <v-row>
                        <!-- Calle -->
                        <v-text-field label="Calle" v-model="prospecto.calle"
                        :error-messages="calleError" :counter="40" @input="$v.prospecto.calle.$touch()"
                        @blur="$v.prospecto.calle.$touch()" required class="pl-4 pr-7" />
                        <!-- Número -->
                        <v-text-field label="Número" v-model="prospecto.numero"
                        :error-messages="numeroError" :counter="4" @input="$v.prospecto.numero.$touch()"
                        @blur="$v.prospecto.numero.$touch()" required class="pr-7" />
                        <!-- Colonia -->
                        <v-text-field label="Colonia" v-model="prospecto.colonia"
                        :error-messages="coloniaError" :counter="30" @input="$v.prospecto.colonia.$touch()"
                        @blur="$v.prospecto.colonia.$touch()" required class="pr-7" />
                    </v-row>
                    <v-row>
                        <!-- Código Postal -->
                        <v-text-field label="Código Postal" v-model="prospecto.cp"
                        :error-messages="cpError" :counter="5" @input="$v.prospecto.cp.$touch()"
                        @blur="$v.prospecto.cp.$touch()" required class="pl-4 pr-7" />
                        <!-- Teléfono -->
                        <v-text-field label="Teléfono" v-model="prospecto.telefono"
                        :error-messages="telefonoError" :counter="10" @input="$v.prospecto.telefono.$touch()"
                        @blur="$v.prospecto.telefono.$touch()" required class="pr-7" />
                        <!-- RFC -->
                        <v-text-field label="RFC" v-model="prospecto.rfc"
                        :error-messages="rfcError" :counter="13" @input="$v.prospecto.rfc.$touch()"
                        @blur="$v.prospecto.rfc.$touch()" required class="pr-7" />
                    </v-row>
                    <!-- Cargar documentos -->
                    <v-col md=6>
                    <v-row class="mb-1">
                    <span class="font-italic font-weight-light headline">Cargar documentos - (PDF)</span>
                    <v-spacer></v-spacer>
                    </v-row>
                    <v-divider class="ml-n2 teal"></v-divider>
                    <v-row class="ml-n1">
                        <v-text-field label="Nombre Documento" v-model="documento.nombredoc"
                        :error-messages="docError" :counter="20" @input="$v.documento.nombredoc.$touch()"
                        @blur="$v.documento.nombredoc.$touch()" required="false" class="pr-7" />
                        <!-- Cargar  -->
                        <v-file-input v-model="documento.documento" placeholder="Cargar tu documento" 
                        label="File input" prepend-icon="mdi-paperclip" @change="updateFile"
                        type="file" accept=".pdf"
                        :error-messages="fileError" @input="$v.documento.documento.$touch()"
                        @blur="$v.documento.documento.$touch()" required="false"
                        >
                            <template v-slot:selection="{ text }">
                                <v-chip small label color="primary">{{ text }}</v-chip>
                            </template>
                        </v-file-input>
                         <v-icon class="ml-8 mt-n2" big @click="nuevoDoc"> mdi-file-plus</v-icon>
                    </v-row>
                    <v-row>
                        <!-- Aqui van los cargados -->
                    <v-col md="8" class="font-weight-light title ml-2" >
                    <v-row>
                        <span class="ml-2 mb-2 font-weight-light headline">Documentos agregados</span>
                    </v-row>
                    <v-divider class="ml-n2 mr-n12 mb-2 teal"></v-divider>
                                <v-row v-for="(documento, index) in documentos" :key="index">
                                <v-icon big class="mr-2"> mdi-file-check</v-icon>
                                - <span class="ml-2 font-italic">
                                    {{ documento.nombredoc }}
                                  </span>
                                 <v-spacer></v-spacer>
                                <v-icon class="mr-5" big @click="removeFile(index)"> mdi-delete</v-icon>
                                </v-row>
                        </v-col>
                    </v-row>
                    </v-col>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn @click="exit" class="error font-weight-light subtitle-1">Salir</v-btn>
                    <v-btn type="submit" class="primary font-weight-light subtitle-1">Enviar</v-btn>
                    </v-card-actions>
                </form>
            </v-card-text>
        </v-card>
        <div>
        </div>
    </v-container>
</template>

<script>
import ProspectoService from '../services/ProspectoService'
import DocumentoService from '../services/DocumentoService'
import { required, maxLength, numeric, alphaNum, minLength} from 'vuelidate/lib/validators'
export default {
    data() {
        return{
            documentos: [],
            documento:{
                idprospecto: null,
                iddocumento:null,
                nombredoc: null,
                documento: null,
            },
            archivo: null,
            prospecto:{
            nombre: null,
            apellidopat: null,
            apellidomat: null,
            calle: null,
            numero: null,
            colonia: null,
            cp: null,
            telefono: null,
            rfc: null,
            estatus: 'Enviado',
            },
            docAgregado: false,
            exitAlert: false,
            submitStatus: null,
            error: null,
            type: null,
            elapse: null,
            itemsNav: [ {
                text: 'Home',
                disabled: false,
                href: '/'
                },
                {
                text: 'Captura',
                disabled: true,
                href: '/capturaprospecto'
                }
            ]
        }
    },
    validations:{
        prospecto:{
            nombre: {required, maxLength: maxLength(20)},
            apellidopat: {required, maxLength: maxLength(20)},
            apellidomat: {maxLength: maxLength(20)},
            calle: {required, maxLength: maxLength(40)},
            numero: {required, maxLength: maxLength(4), minLength: minLength(4),numeric},
            colonia: {required, maxLength: maxLength(30)},
            cp: {required, maxLength: maxLength(5),minLength: minLength(5),numeric},
            telefono: {required, maxLength: maxLength(10),minLength: minLength(10),numeric},
            rfc: {required, maxLength: maxLength(13),minLength: minLength(12),alphaNum}, 
        },
        documento:{
            nombredoc: {required, maxLength: maxLength(20)},
            documento: {required}
        }
    },
    prospectoService : null,
    documentoService: null,
    computed: {
        nombreError () {
        const errors = []
        if(!this.$v.prospecto.nombre.$dirty) return errors
        !this.$v.prospecto.nombre.maxLength && errors.push('Nombre debe ser máximo 20 caracteres')
        !this.$v.prospecto.nombre.required && errors.push('Nombre es requerido')
        return errors
        },
        apellidopatError() {
        const errors = []
        if(!this.$v.prospecto.apellidopat.$dirty) return errors
        !this.$v.prospecto.apellidopat.maxLength && errors.push('Máximo 20 caracteres')
        !this.$v.prospecto.apellidopat.required && errors.push('Apellido Paterno es requerido')
        return errors
        },
        apellidomatError() {
        const errors = []
        if(!this.$v.prospecto.apellidomat.$dirty) return errors
        !this.$v.prospecto.apellidomat.maxLength && errors.push('Máximo 20 caracteres')
        return errors
        },
        calleError() {
        const errors = []
        if(!this.$v.prospecto.calle.$dirty) return errors
        !this.$v.prospecto.calle.required && errors.push('Calle es requerida')
        !this.$v.prospecto.calle.maxLength && errors.push('Máximo 40 caracteres')
        return errors
        },
        numeroError() {
        const errors = []
        if(!this.$v.prospecto.numero.$dirty) return errors
        !this.$v.prospecto.numero.required && errors.push('Número es requerido')
        !this.$v.prospecto.numero.maxLength && errors.push('Máximo 4 caracteres')
        !this.$v.prospecto.numero.numeric && errors.push('Sólo se aceptan números')
        !this.$v.prospecto.numero.minLength && errors.push('Deben ser 4 caracteres')
        return errors
        },
        coloniaError() {
        const errors = []
        if(!this.$v.prospecto.colonia.$dirty) return errors
        !this.$v.prospecto.colonia.required && errors.push('Colonia es requerida')
        !this.$v.prospecto.colonia.maxLength && errors.push('Máximo 30 caracteres')
        return errors
        },
        cpError(){
            const errors = []
            if(!this.$v.prospecto.cp.$dirty) return errors
            !this.$v.prospecto.cp.required && errors.push('Código Postal es requerido')
            !this.$v.prospecto.cp.maxLength && errors.push('Máximo 5 caracteres');
            !this.$v.prospecto.cp.numeric && errors.push('Sólo se aceptan números')
            !this.$v.prospecto.cp.minLength && errors.push('Deben ser 5 caracteres')
            return errors
        },
        telefonoError(){
            const errors = []
            if(!this.$v.prospecto.telefono.$dirty) return errors
            !this.$v.prospecto.telefono.required && errors.push('Teléfono es requerido')
            !this.$v.prospecto.telefono.maxLength && errors.push('Máximo 10 caracteres')
            !this.$v.prospecto.telefono.numeric && errors.push('Sólo se aceptan números')
            !this.$v.prospecto.telefono.minLength && errors.push('Deben ser 10 caracteres')
            return errors
        },
        rfcError(){
            const errors = []
            if(!this.$v.prospecto.rfc.$dirty) return errors
            !this.$v.prospecto.rfc.required && errors.push('RFC es requerido')
            !this.$v.prospecto.rfc.alphaNum && errors.push('Solo valores alfanumericos')
            !this.$v.prospecto.rfc.maxLength && errors.push('Máximo 13 caracteres');
            !this.$v.prospecto.rfc.minLength && errors.push('Mínimo 12 caracteres')
            return errors
        },
        docError(){
            const errors = []
            if(!this.$v.documento.nombredoc.$dirty) return errors
            !this.$v.documento.nombredoc.required && errors.push('Nombre es requerido')
            !this.$v.documento.nombredoc.maxLength && errors.push('Máximo 20 caracteres')
            return errors
        },
        fileError(){
            const errors = []
            if(!this.$v.documento.documento.$dirty) return errors
            !this.$v.documento.documento.required && errors.push('Documento es requerido')
            return errors
        }
    },
    created(){
        this.prospectoService = new ProspectoService()
        this.documentoService = new DocumentoService()
    },
    methods:{
        submit(){
            this.$v.$touch()
            if(this.$v.$invalid){
                this.submitStatus='ERROR'
                this.showAlertError('error')
            }else{
            this.prospectoService.save(this.prospecto).then(data => {
                if(data.status === 200){
                    this.$v.$reset()
                    this.showAlert('success')
                    this.prospecto={
                    nombre: null,
                    apellidopat: null,
                    apellidomat: null,
                    calle: null,
                    numero: null,
                    colonia: null,
                    cp: null,
                    telefono: null,
                    rfc: null,
                    estatus: 'Enviado'
                }
                    
                    console.log()
                    let numero= 1
                    this.documentos.forEach(doc=>{
                        doc.idprospecto= data.data.idprospecto
                        doc.iddocumento= numero;
                        numero++
                    })
                    }
                    this.documentos.forEach(documento=>{
                        this.documentoService.save(documento).then(data => {
                        if(data.status === 200){
                        this.documentos = [],
                        this.documento = {
                            idprospecto: null,
                            iddocumento:null,
                            nombredoc: null,
                            documento: null,
                             }
                           }
                       })
                    })
                    
                })
                        
            }
        },
        updateFile(files){
            let reader = new FileReader();
            reader.readAsDataURL(files);
            reader.onloadend = () =>{
                this.documentos.push({
                    id: null,
                    iddocumento:null,
                    nombredoc: this.documento.nombredoc,
                    documento: reader.result
                })
                this.archivo = reader.result
            }
        },
        nuevoDoc(){
            this.$v.documento.$touch()
            if(this.$v.documento.$invalid){
                this.showAlertError('error')
            }else{
                
            this.documento.nombredoc = null
            this.documento.documento = null
            this.docAgregado = true
            this.$v.$reset()
            }
        },
        removeFile(fileKey){
            this.documentos.splice(fileKey,1)
        },
        showAlert (type) {
            this.type = type
            let timer = this.showAlert.timer
            if (timer) {
                clearTimeout(timer)
                }
            this.showAlert.timer = setTimeout(() => {
                this.type = null}, 2200)
                this.elapse = 1 
                let t = this.showAlert.t
            if (t) {
                clearInterval(t)
                 }
        },
        showAlertError (type) {
            this.error = type
            let timer = this.showAlert.timer
            if (timer) {
                clearTimeout(timer)
                }
            this.showAlert.timer = setTimeout(() => {
                this.error = null}, 2200)
                this.elapse = 1 
                let t = this.showAlert.t
            if (t) {
                clearInterval(t)
                 }
        },
        exit(){
            this.exitAlert = true
        },
        exitConfirm(){
            this.$v.$reset()
            this.$router.push({ name: 'home'})
            this.prospecto={
                nombre: null,
                apellidopat: null,
                apellidomat: null,
                calle: null,
                numero: null,
                colonia: null,
                cp: null,
                telefono: null,
                rfc: null,
                estatus: 'Enviado'
                }
        },
        closeAlert(){
            this.exitAlert=!this.exitAlert
        }
    }
}
</script>