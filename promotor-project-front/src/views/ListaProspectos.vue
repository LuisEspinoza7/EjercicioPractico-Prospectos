<template>
<v-container fluid>
    <div class="mt-n3 font-weight-bold overline">
        <v-breadcrumbs large :items="itemsNav" divider=">"></v-breadcrumbs>
    </div>
    <div style="margin:0 auto;">
    <Panel style="font-size:27px;" header="Gestionar Prospectos">
    <Menubar class="subtitle-1" :model="items"/>
     <DataTable :value="prospectos" :paginator="true" 
     :rows="5" selectionMode="single" :selection.sync="selectedProspecto" dataKey="idprospecto">
        <Column field="nombre" header="Nombre"></Column>
        <Column field="apellidopat" header="Apellido Paterno"></Column>
        <Column field="apellidomat" header="Apellido Materno"></Column>
        <Column field="estatus" header="Estatus"></Column>
    </DataTable>
    </Panel>
    </div>
</v-container>
</template>

<script>
import ProspectoService from '../services/ProspectoService'
  export default {
    data () {
      return {
        
        items:[
            {
                label: 'Total de prospectos',
                icon: 'pi pi-list',
                command: ()=>{
                    this.getAll()
                }
            },
            {
                label: 'Pendientes por evaluar',
                icon: 'pi pi-list',
                command: ()=>{
                    this.getPendientes()
                }
            },
            {
                label: 'Visualizar prospecto',
                icon: 'pi pi-id-card',
                command: ()=>{
                    this.selectProspecto()
                }
            },
        ],
        prospectos: null,
        prospectosPend: [],
        selected: null,
        selectedProspecto: {
            id: null,
            nombre: null,
        },
        itemsNav: [ {
            text: 'Home',
            disabled: false,
            href: '/'
            },
            {
            text: 'Gestionar Prospectos',
            disabled: true,
            href: '/listaprospectos'
            }
        ]
      }
    },
    prospectoService : null,
    created(){
        this.prospectoService = new ProspectoService()
    },
    mounted() {
        this.getAll()
    },
    methods: {
        getAll(){
            this.prospectoService.getAll().then(data=>{
                this.prospectos=data.data;
            })
            this.prospectosPend = []
        },
        getPendientes(){
                this.prospectosPend = []
                this.prospectos.forEach((element)=>{
                    if(element.estatus === 'Enviado'){
                        this.prospectosPend.push(element)
                    }
                })
                this.prospectos = this.prospectosPend
        },
        selectProspecto(){
            if(this.selectedProspecto.idprospecto != null){
                this.$router.push({name: 'prospectodetails', params:{ idprospecto: this.selectedProspecto.idprospecto}})
            }else{
                alert('Favor de seleccionar un prospecto para visualizarlo')
            }
        }
    }
  }
</script>