function toggleVisible(idName) {
    var div = document.getElementById(idName);
    if (div.style.display !== 'none') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'inline-block';
    }
}

function lastUpdated() {
    var now = new Date();
    var nowString = now.toString();
    document.getElementById("now").innerHTML = nowString;
}