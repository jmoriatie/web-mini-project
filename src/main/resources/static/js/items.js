$(document).ready( getItems(1) );

function getItems(page) {
    $('#itemList').empty();
    const inputData = $('#keyword').val();
    $.ajax({
        method: "get",
        url: "/item/itemList", // 바꿔야함
        data:{"keyword": inputData, "page":page},
        dataType: "html"
    }).done(
        function (itemList){
            console.log(itemList);
            $('#itemList').replaceWith(itemList);
        }
    )
}
