import Vue from 'vue';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);
import colors from 'vuetify/lib/util/colors'

export default new Vuetify({
    theme: {
        themes: {
          light: {
            // primary: colors.teal.darken4,
            primary: '#183f4a',
            secondary: colors.grey.darken1,
            accent: colors.shades.black,
            error: colors.red.darken2,
          },
          dark: {
            // primary: colors.grey.darken4,
            primary: '#183f4a',
            error: colors.red.darken2,
          },
        },
      },
});
