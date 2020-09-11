<template>
  <v-footer dark padless>
    <v-card class="flex" flat tile>
      <v-card-text class="py-2 white--text text-center title">
        {{ new Date().getFullYear() }} — <strong>Empresa</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
  })
}
</script>
