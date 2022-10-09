
function getItems() {
    $('#itemList').empty();
    const inputData = $('#search').val();
    $.ajax({
        method: "get",
        url: "/item/item-test", // 바꿔야함
        data:{"keyword": inputData},
        dataType: "html"
    }).done(
        function (itemList){
            console.log(itemList);
            $('#itemList').replaceWith(itemList);
        }
    )
}
