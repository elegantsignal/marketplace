function passwordValidation(password, repeat) {
  if (password.length < 6) {
    return "Min password length is 6";
  }
  return password != repeat ? "Passwords do not match." : ""
}


$(document).ready(function () {
  $.ajaxSetup({ cache: false });
  $('#search').keyup(function () {
    $('#result').html('');
    $('#state').val('');
    var searchField = $('#search').val();
    $.getJSON('/search/' + searchField, function (data) {
      if (data.length > 0){
       $('#result').addClass("show");
      }
      else {
        $('#result').removeClass("show");
      }
      $.each(data, function (key, value) {
        $('#result').append(
          // `<a class="dropdown-item" href="#">Action</a>`
          `<li class="dropdown dropdown-item"><a href="/book/${value.id}">${value.title}</a> | <span class="text-muted">${value.price}</span></li>`
        );
      });
    });
  });


  $(window).click(function() {
    $('#result').removeClass("show");
    });
    
    $('.dropdown').click(function(event){
        event.stopPropagation();
    });

    
});
