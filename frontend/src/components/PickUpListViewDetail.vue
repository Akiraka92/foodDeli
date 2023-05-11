<template>

    <v-card outlined>
        <v-card-title>
            PickUpList # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="OrderId" v-model="item.orderId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="RestaurantNo" v-model="item.restaurantNo" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="UserNo" v-model="item.userNo" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Status" v-model="item.status" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Adress" v-model="item.adress" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'PickUpListViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/pickUpLists/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

