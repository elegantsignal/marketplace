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
    if (searchField.length < 3) {
      $('#result').removeClass("show");
      return
    }
    $.getJSON('/search/' + searchField, function (data) {
      if (data.length > 0) {
        $('#result').addClass("show");
      }
      else {
        $('#result').removeClass("show");
      }
      $.each(data, function (key, value) {
        $('#result').append(
          `
        <div class="dropdown-item">
          <div class="row">
            <div class="col-auto" style="min-width: 100px;">
              <img class="img-thumbnail" style="height: 50px;" src="/${value.cover}" alt="${value.title}">
            </div>
            <div class="col">
              <a href="/book/${value.id}">${value.title}</a><br>by ${value.author} | <span class="text-muted">$${value.price}</span>
            </div>
          </div>
        </div>
        <div class="dropdown-divider"></div>
        `
        );
      });
    });
  });


  $(window).click(function () {
    $('#result').removeClass("show");
  });

  $('.select-me').click(function() {
      $(this).select();
  });
});
