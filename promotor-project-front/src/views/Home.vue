<template>
    <v-container fluid>
        <div class="mt-n3 font-weight-bold overline">
            <v-breadcrumbs large :items="itemsNav" divider=">"></v-breadcrumbs>
        </div>
        <v-carousel :show-arrows="true">
            <v-carousel-item
            v-for="(item,i) in items"
            :key="i"
            :src="item.src"
            ></v-carousel-item>
        </v-carousel>
    </v-container>
</template>

<script>
export default {
    data(){
        return {
            itemsNav: [
                {
                    text: 'Home',
                    disabled: true,
                    href: '/'
                }
            ],
            items: [
                {
                    src: require('../assets/choosecustomers.jpg')
                },
                                {
                    src: require('../assets/stats.jpg')
                }
            ]
        }
    },
    methods: {
        clickNuevo(){
            this.$router.push({ name: 'capturaprospecto'})
        }
    }
}
</script>

<style scoped>
.btn-c{
    height:40px; 
    width:364px;
}
</style>