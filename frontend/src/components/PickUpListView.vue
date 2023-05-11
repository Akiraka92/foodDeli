<template>

    <v-data-table
        :headers="headers"
        :items="pickUpList"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'PickUpListView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "orderId", value: "orderId" },
                { text: "restaurantNo", value: "restaurantNo" },
                { text: "userNo", value: "userNo" },
                { text: "status", value: "status" },
                { text: "adress", value: "adress" },
            ],
            pickUpList : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/pickUpLists'))

            temp.data._embedded.pickUpLists.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.pickUpList = temp.data._embedded.pickUpLists;
        },
        methods: {
        }
    }
</script>

