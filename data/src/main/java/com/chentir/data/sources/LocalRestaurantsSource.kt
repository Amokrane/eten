package com.chentir.data.sources

import com.chentir.data.dto.VenuesResult
import com.chentir.data.mappers.toRestaurant
import com.chentir.domain.RestaurantsSource
import com.chentir.domain.entities.Restaurant
import com.google.gson.Gson

class LocalRestaurantsSource : RestaurantsSource {
    override suspend fun searchRestaurants(lat: Double, lng: Double): List<Restaurant> {
        val venuesResult = Gson().fromJson(
            """
            {"meta":{"code":200,"requestId":"5fa5277bdc3fa014e6092aba"},"response":{"venues":[{"id":"4adcda14f964a5203a3721e3","name":"Carette","location":{"address":"4 place du Trocadéro","lat":48.86358902223995,"lng":2.287205457687378,"labeledLatLngs":[{"label":"display","lat":48.86358902223995,"lng":2.287205457687378}],"distance":16384,"postalCode":"75016","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["4 place du Trocadéro","75016 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d1dc931735","name":"Tea Room","pluralName":"Tea Rooms","shortName":"Tea Room","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/tearoom_","suffix":".png"},"primary":true}],"venuePage":{"id":"79891414"},"referralId":"v-1604659068","hasPerk":false},{"id":"4f129ec2c2ee45f86a798910","name":"Little Kitchen","location":{"address":"30 rue de l\u2019Eglise","lat":48.863773,"lng":2.44391,"labeledLatLngs":[{"label":"display","lat":48.863773,"lng":2.44391}],"distance":27585,"postalCode":"93100","cc":"FR","city":"Montreuil","state":"Île-de-France","country":"France","formattedAddress":["30 rue de l\u2019Eglise","93100 Montreuil","France"]},"categories":[{"id":"4bf58dd8d48988d14e941735","name":"American Restaurant","pluralName":"American Restaurants","shortName":"American","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/default_","suffix":".png"},"primary":true}],"venuePage":{"id":"544017552"},"referralId":"v-1604659068","hasPerk":false},{"id":"4d230917b69c6dcb80718795","name":"Franprix","location":{"address":"8 Avenue George Sand","lat":48.90733,"lng":2.36479,"labeledLatLngs":[{"label":"display","lat":48.90733,"lng":2.36479}],"distance":21412,"postalCode":"93210","cc":"FR","city":"Saint-Denis","state":"Île-de-France","country":"France","formattedAddress":["8 Avenue George Sand","93210 Saint-Denis","France"]},"categories":[{"id":"52f2ab2ebcbc57f1066b8b46","name":"Supermarket","pluralName":"Supermarkets","shortName":"Supermarket","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/shops\/food_grocery_","suffix":".png"},"primary":true}],"venuePage":{"id":"552037322"},"referralId":"v-1604659068","hasPerk":false},{"id":"4b867a97f964a520e48b31e3","name":"L'As du Fallafel","location":{"address":"34 rue des Rosiers","lat":48.85741376683434,"lng":2.3590779304504395,"labeledLatLngs":[{"label":"display","lat":48.85741376683434,"lng":2.3590779304504395}],"distance":21645,"postalCode":"75004","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["34 rue des Rosiers","75004 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d10b941735","name":"Falafel Restaurant","pluralName":"Falafel Restaurants","shortName":"Falafel","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/falafel_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"4af77f16f964a520970922e3","name":"La Perle","location":{"address":"78 rue Vieille du Temple","lat":48.85998512078513,"lng":2.3607351500162,"labeledLatLngs":[{"label":"display","lat":48.85998512078513,"lng":2.3607351500162}],"distance":21696,"postalCode":"75003","cc":"FR","neighborhood":"Marais","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["78 rue Vieille du Temple","75003 Paris","France"]},"categories":[{"id":"52e81612bcbc57f1066b79f1","name":"Bistro","pluralName":"Bistros","shortName":"Bistro","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/default_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"4adcda04f964a520323221e3","name":"Café de Flore","location":{"address":"172 boulevard Saint-Germain","lat":48.85399681424528,"lng":2.3326457751586753,"labeledLatLngs":[{"label":"display","lat":48.85399681424528,"lng":2.3326457751586753}],"distance":19878,"postalCode":"75006","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["172 boulevard Saint-Germain","75006 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d16d941735","name":"Café","pluralName":"Cafés","shortName":"Café","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/cafe_","suffix":".png"},"primary":true}],"venuePage":{"id":"76110098"},"referralId":"v-1604659068","hasPerk":false},{"id":"591624431b0ea52ea821c07b","name":"Ground Control","location":{"address":"81 rue du Charolais","crossStreet":"Rue de Rambouillet","lat":48.843288753397985,"lng":2.3810898189565886,"labeledLatLngs":[{"label":"routing","lat":48.84384741149501,"lng":2.3817265033721924},{"label":"display","lat":48.843288753397985,"lng":2.3810898189565886}],"distance":23620,"postalCode":"75012","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["81 rue du Charolais (Rue de Rambouillet)","75012 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d117941735","name":"Beer Garden","pluralName":"Beer Gardens","shortName":"Beer Garden","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/nightlife\/beergarden_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"4b066e0ff964a520c2eb22e3","name":"Starbucks","location":{"address":"76-78 avenue des Champs-Élysées","crossStreet":"Galerie des Arcades","lat":48.87142632601174,"lng":2.3043715953826904,"labeledLatLngs":[{"label":"display","lat":48.87142632601174,"lng":2.3043715953826904}],"distance":17388,"postalCode":"75008","cc":"FR","neighborhood":"Champs-Élysées, Paris","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["76-78 avenue des Champs-Élysées (Galerie des Arcades)","75008 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d1e0931735","name":"Coffee Shop","pluralName":"Coffee Shops","shortName":"Coffee Shop","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/coffeeshop_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"4b9e0d9df964a52045c836e3","name":"McDonald's","location":{"address":"98 Boulevard Saint Germain","lat":48.851412478025566,"lng":2.343781292438507,"labeledLatLngs":[{"label":"display","lat":48.851412478025566,"lng":2.343781292438507}],"distance":20742,"postalCode":"75005","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["98 Boulevard Saint Germain","75005 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d16e941735","name":"Fast Food Restaurant","pluralName":"Fast Food Restaurants","shortName":"Fast Food","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/fastfood_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5c38cbde1987ec002bc233ab","name":"Eataly Paris Marais","location":{"address":"37 rue Sainte-Croix de la Bretonnerie","lat":48.85862515030205,"lng":2.3545520907270636,"labeledLatLngs":[{"label":"display","lat":48.85862515030205,"lng":2.3545520907270636}],"distance":21291,"postalCode":"75004","cc":"FR","neighborhood":"Hôtel-de-Ville","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["37 rue Sainte-Croix de la Bretonnerie","75004 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d146941735","name":"Deli \/ Bodega","pluralName":"Delis \/ Bodegas","shortName":"Deli \/ Bodega","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/deli_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"4adcda12f964a520903621e3","name":"Café de l'Esplanade","location":{"address":"52 rue Fabert","lat":48.85829011894279,"lng":2.3110073804855347,"labeledLatLngs":[{"label":"display","lat":48.85829011894279,"lng":2.3110073804855347}],"distance":18223,"postalCode":"75007","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["52 rue Fabert","75007 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d10c941735","name":"French Restaurant","pluralName":"French Restaurants","shortName":"French","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/french_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"4adcda04f964a520503221e3","name":"Le Café Marly","location":{"address":"93 rue de Rivoli","crossStreet":"Musée du Louvre","lat":48.8617397900653,"lng":2.335646152496338,"labeledLatLngs":[{"label":"display","lat":48.8617397900653,"lng":2.335646152496338}],"distance":19864,"postalCode":"75001","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["93 rue de Rivoli (Musée du Louvre)","75001 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d16d941735","name":"Café","pluralName":"Cafés","shortName":"Café","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/cafe_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"4adcda04f964a520273221e3","name":"Les Deux Magots","location":{"address":"6 place Saint-Germain-des-Prés","lat":48.853999335657115,"lng":2.3331602155448303,"labeledLatLngs":[{"label":"display","lat":48.853999335657115,"lng":2.3331602155448303}],"distance":19914,"postalCode":"75006","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["6 place Saint-Germain-des-Prés","75006 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d16d941735","name":"Café","pluralName":"Cafés","shortName":"Café","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/cafe_","suffix":".png"},"primary":true}],"venuePage":{"id":"79891423"},"referralId":"v-1604659068","hasPerk":false},{"id":"4bbe191ba8cf76b054a5b2fd","name":"G20","location":{"address":"147 rue Saint-Dominique","lat":48.85807246194297,"lng":2.3011465525643997,"labeledLatLngs":[{"label":"display","lat":48.85807246194297,"lng":2.3011465525643997}],"distance":17539,"postalCode":"75007","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["147 rue Saint-Dominique","75007 Paris","France"]},"categories":[{"id":"4d954b0ea243a5684a65b473","name":"Convenience Store","pluralName":"Convenience Stores","shortName":"Convenience Store","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/shops\/conveniencestore_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"4bc5e23151b376b0ce8e1a6f","name":"Ladurée","location":{"address":"75 avenue des Champs-Élysées","crossStreet":"Rue Lincoln","lat":48.870780615282726,"lng":2.3030948638916016,"labeledLatLngs":[{"label":"display","lat":48.870780615282726,"lng":2.3030948638916016}],"distance":17313,"postalCode":"75008","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["75 avenue des Champs-Élysées (Rue Lincoln)","75008 Paris","France"]},"categories":[{"id":"5744ccdfe4b0c0459246b4e2","name":"Pastry Shop","pluralName":"Pastry Shops","shortName":"Pastry","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/dessert_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5f5d036454e9ca2cdb59ed9c","name":"IT Trattoria","location":{"address":"86 esplanade du Général de Gaulle","lat":48.889989,"lng":2.243485,"labeledLatLngs":[{"label":"display","lat":48.889989,"lng":2.243485}],"distance":12639,"postalCode":"92800","cc":"FR","city":"Puteaux","state":"Île-de-France","country":"France","formattedAddress":["86 esplanade du Général de Gaulle","92800 Puteaux","France"]},"categories":[{"id":"4bf58dd8d48988d110941735","name":"Italian Restaurant","pluralName":"Italian Restaurants","shortName":"Italian","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/italian_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5a3e6731b04056376864ad46","name":"86 Champs (L'Occitane x Pierre Hermé)","location":{"address":"86 AVENUE DES CHAMPS ELYSEES","lat":48.87133271600203,"lng":2.3039243105222207,"labeledLatLngs":[{"label":"display","lat":48.87133271600203,"lng":2.3039243105222207}],"distance":17359,"postalCode":"75008","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["86 AVENUE DES CHAMPS ELYSEES","75008 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d16d941735","name":"Café","pluralName":"Cafés","shortName":"Café","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/cafe_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"4b754d15f964a52026052ee3","name":"KFC","location":{"address":"31-35 boulevard de Sébastopol","crossStreet":"Rue Berger","lat":48.86075868113233,"lng":2.349117547273636,"labeledLatLngs":[{"label":"display","lat":48.86075868113233,"lng":2.349117547273636}],"distance":20848,"postalCode":"75001","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["31-35 boulevard de Sébastopol (Rue Berger)","75001 Paris","France"]},"categories":[{"id":"4d4ae6fc7a7b7dea34424761","name":"Fried Chicken Joint","pluralName":"Fried Chicken Joints","shortName":"Fried Chicken","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/friedchicken_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5894bc5b0b56565043938c18","name":"L'Atelier - Fournil Ephémère","location":{"address":"11 rue de l'Eglise","lat":48.863872,"lng":2.442505,"labeledLatLngs":[{"label":"display","lat":48.863872,"lng":2.442505}],"distance":27482,"postalCode":"93100","cc":"FR","city":"Montreuil","state":"Île-de-France","country":"France","formattedAddress":["11 rue de l'Eglise","93100 Montreuil","France"]},"categories":[{"id":"4bf58dd8d48988d16a941735","name":"Bakery","pluralName":"Bakeries","shortName":"Bakery","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/bakery_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5b06ff12e97dfb002cd47782","name":"Burger King","location":{"address":"4 avenue du Trône","lat":48.8478207389359,"lng":2.3978227517292225,"labeledLatLngs":[{"label":"display","lat":48.8478207389359,"lng":2.3978227517292225}],"distance":24661,"postalCode":"75012","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["4 avenue du Trône","75012 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d16e941735","name":"Fast Food Restaurant","pluralName":"Fast Food Restaurants","shortName":"Fast Food","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/fastfood_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5c927e49625a66002c1f3679","name":"Les Tables de Vélizy","location":{"address":"2 avenue de l'Europe","crossStreet":"Niveau 1","lat":48.78090968189516,"lng":2.2203487323344078,"labeledLatLngs":[{"label":"display","lat":48.78090968189516,"lng":2.2203487323344078}],"distance":17522,"postalCode":"78140","cc":"FR","city":"Vélizy-Villacoublay","state":"Île-de-France","country":"France","formattedAddress":["2 avenue de l'Europe (Niveau 1)","78140 Vélizy-Villacoublay","France"]},"categories":[{"id":"4bf58dd8d48988d120951735","name":"Food Court","pluralName":"Food Courts","shortName":"Food Court","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/shops\/food_foodcourt_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5f7cd1e1793f650240120f67","name":"La Cantinerie","location":{"address":"34 rue Veuve Lacroix","lat":48.904180795467006,"lng":2.23384290933609,"labeledLatLngs":[{"label":"display","lat":48.904180795467006,"lng":2.23384290933609}],"distance":11829,"postalCode":"92250","cc":"FR","city":"La Garenne-Colombes","state":"Île-de-France","country":"France","formattedAddress":["34 rue Veuve Lacroix","92250 La Garenne-Colombes","France"]},"categories":[{"id":"52e81612bcbc57f1066b79f1","name":"Bistro","pluralName":"Bistros","shortName":"Bistro","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/default_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5594f6eb498eb1626ad04d77","name":"Auteuil Brasserie","location":{"address":"78 Rue d'Auteuil","lat":48.848332,"lng":2.259869,"labeledLatLngs":[{"label":"display","lat":48.848332,"lng":2.259869}],"distance":15095,"postalCode":"75016","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["78 Rue d'Auteuil","75016 Paris","France"]},"categories":[{"id":"57558b36e4b065ecebd306b0","name":"Brasserie","pluralName":"Brasseries","shortName":"Brasserie","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/french_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"582f33fefbe8ff78e116c467","name":"Five Guys","location":{"address":"49-51 avenue des Champs-Élysées","crossStreet":"Rue Marbeuf","lat":48.870007868538636,"lng":2.3055893182754517,"labeledLatLngs":[{"label":"display","lat":48.870007868538636,"lng":2.3055893182754517}],"distance":17510,"postalCode":"75008","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["49-51 avenue des Champs-Élysées (Rue Marbeuf)","75008 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d16c941735","name":"Burger Joint","pluralName":"Burger Joints","shortName":"Burgers","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/burger_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5af9704d75eee4002c90862c","name":"La Felicità","location":{"address":"55 boulevard Vincent Auriol","crossStreet":"5 parvis Alan Turing","lat":48.832280245161556,"lng":2.3727017862231095,"labeledLatLngs":[{"label":"display","lat":48.832280245161556,"lng":2.3727017862231095}],"distance":23426,"postalCode":"75013","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["55 boulevard Vincent Auriol (5 parvis Alan Turing)","75013 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d110941735","name":"Italian Restaurant","pluralName":"Italian Restaurants","shortName":"Italian","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/italian_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5f575b674a7b4a1d056682e9","name":"Supu Ramen","location":{"address":"58 Rue du Faubourg Poissonnière","lat":48.875161,"lng":2.348226,"labeledLatLngs":[{"label":"display","lat":48.875161,"lng":2.348226}],"distance":20466,"postalCode":"75010","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["58 Rue du Faubourg Poissonnière","75010 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d111941735","name":"Japanese Restaurant","pluralName":"Japanese Restaurants","shortName":"Japanese","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/japanese_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5f7d9e7178c5442373bf83ea","name":"O Copains D\u2019abord","location":{"address":"38 Grande Rue","lat":48.967309,"lng":1.933503,"labeledLatLngs":[{"label":"display","lat":48.967309,"lng":1.933503}],"distance":12319,"postalCode":"78130","cc":"FR","city":"Chapet","state":"Île-de-France","country":"France","formattedAddress":["38 Grande Rue","78130 Chapet","France"]},"categories":[{"id":"4bf58dd8d48988d10c941735","name":"French Restaurant","pluralName":"French Restaurants","shortName":"French","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/french_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5f576e400b69a228a116d9b7","name":"Salad & Me","location":{"address":"10 Rue De General De Castelnau","lat":48.85006,"lng":2.300903,"labeledLatLngs":[{"label":"display","lat":48.85006,"lng":2.300903}],"distance":17806,"postalCode":"75015","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["10 Rue De General De Castelnau","75015 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d1bd941735","name":"Salad Place","pluralName":"Salad Places","shortName":"Salad","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/salad_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5f81ffb3f7ff38369988dfe5","name":"Kroran - Restaurant Ouighour","location":{"lat":48.868195,"lng":2.352088,"labeledLatLngs":[{"label":"display","lat":48.868195,"lng":2.352088}],"distance":20882,"postalCode":"75002","cc":"FR","city":"Paris","state":"Île-de-France","country":"France","formattedAddress":["75002 Paris","France"]},"categories":[{"id":"4bf58dd8d48988d1c4941735","name":"Restaurant","pluralName":"Restaurants","shortName":"Restaurant","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/default_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false},{"id":"5f57dd0601868c75ae2f246e","name":"Zarda Food","location":{"lat":48.906193,"lng":2.3893046,"labeledLatLngs":[{"label":"display","lat":48.906193,"lng":2.3893046}],"distance":23204,"postalCode":"93300","cc":"FR","city":"Aubervilliers","state":"Île-de-France","country":"France","formattedAddress":["93300 Aubervilliers","France"]},"categories":[{"id":"4bf58dd8d48988d1c4941735","name":"Restaurant","pluralName":"Restaurants","shortName":"Restaurant","icon":{"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/default_","suffix":".png"},"primary":true}],"referralId":"v-1604659068","hasPerk":false}],"confident":false}}
""", VenuesResult::class.java
        )
        return venuesResult.response.venues.map {
            it.toRestaurant()
        }
    }
}
