function deleteCharacter() {
    let currentValue = $('.inputDisplay').val();
    $('.inputDisplay').val(currentValue.substring(0, currentValue.length - 1));
}

function clearInput() {
    $('.inputDisplay').val('');
}

function insertCharacter(char) {
    let currentValue = $('.inputDisplay').val();
    let length = currentValue.length;
    let flag = false;
    if(char == '+' || char == '-' || char == '*' || char == '/')
        flag = true;
    if(length == 0)
    {
        // eğer ilk başta sayı girmeden operatör yazdıysan hiçbirşey yapmaz
        if(flag)
        return;
    }
    let flagNew = false;
    let lastCharacter = currentValue[length-1];
    if(lastCharacter == '+' || lastCharacter == '-' || lastCharacter == '*' || lastCharacter == '/')
    flagNew = true;
    if(flag && flagNew)
    $('.inputDisplay').val(currentValue.substring(0,length-1) + char);  // fazla operatör koymayı engeller 
    else
    $('.inputDisplay').val($('.inputDisplay').val() + char);            // normal karakter eklenir. 
}

function result() {
    let currentValue = $('.inputDisplay').val();
    let length = currentValue.length;  
    let flag   = false;                  
    let char   = currentValue[length-1]; 
    if(char == '+' || char == '-' || char == '*' || char == '/')
        flag = true;
    if(flag)
        $('.inputDisplay').val("ERROR!");                           // ikinci sayı yazmadan işlem sonucu beklemek 
    else
        $('.inputDisplay').val(eval($('.inputDisplay').val()));     // eval javascript kütüphanesinden 
}


