
(function() {
    function MapView(el){
        this.el = el;
        this.initMap();
        this.icons = {};
    }

    MapView.prototype.initMap = function() {
        this.map = L.map(this.el).setView([52.232222, 21.008333], 6);

        L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(this.map);
    };

    MapView.prototype.addMarker = function(lat, lng, href){
        var marker = L.marker([lat, lng]).addTo(this.map);
        if(marker){
            marker.on('click', function(){
                document.location = href;
            });
        }
    };

    Array.prototype.slice.call(document.querySelectorAll('[data-map][data-lat][data-lng]')).forEach(function(el){
        var map = new MapView(el);
        var lat = el.getAttribute('data-lat');
        var lng = el.getAttribute('data-lng');

        map.addMarker(lat, lng);
    });

    Array.prototype.slice.call(document.querySelectorAll('[data-map][data-url]')).forEach(function(el){
        var url = el.getAttribute('data-url');
        var map = new MapView(el);
        $.ajax(url).done(function(data) {
            data.forEach(function (t) {
                map.addMarker(t.point.lat, t.point.lng, t.link);
            })
        })
    });

}) ();

(function($) {
    $('.point-select').each(function(i, item){
        var $el = $(item);
        var $input_lat = $el.find('.field-lat');
        var $input_lng = $el.find('.field-lng');
        var $input_address = $el.find('.field-address');
        var $map_canvas = $el.find('.map-canvas');
        debugger;
        var options = {
            radius: 0,
            location: {
                latitude: 52.2296756,
                longitude: 21.0122287
            },
            zoom: 6,
            inputBinding: {
                latitudeInput: $input_lat,
                longitudeInput: $input_lng,
                locationNameInput: $input_address
            },
            enableAutocomplete: true,
        };
        if( $input_lat.val() && !isNaN($input_lat.val()) && $input_lng.val() && !isNaN($input_lng.val()) ){
            console.log($input_lat.val(), $input_lng.val());
            options['location'] = {
                latitude: $input_lat.val(),
                longitude: $input_lng.val()
            }
            console.log("AAAAAAA", options);
        }
        $map_canvas.locationpicker(options);
    })
})(jQuery);