$(function() {
    var category = $('input[name="category"]:checked').val();
    showhideStar(category);

    $('input[name="category"]').change(function(){
        showhideStar($('input[name="category"]:checked').val());
    });
});

function showhideStar(category){
    if(category != '02'){
        $('#codingDiffSector').hide();
    }else{
        $('#codingDiffSector').show();
    }
}