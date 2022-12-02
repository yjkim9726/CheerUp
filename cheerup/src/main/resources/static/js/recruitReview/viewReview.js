$(function() {
    var category = $('input[name="category"]:checked').val();
    if(category != '02'){
        $('.codingDiffSector').hide();
    }else{
        $('.codingDiffSector').show();
    }

    $('input[name="md-category"]').change(function(){
        var category = $('input[name="md-category"]:checked').val();
        if(category != '02'){
            $('.md-codingDiffSector').hide();
        }else{
            $('.md-codingDiffSector').show();
        }
    });
});