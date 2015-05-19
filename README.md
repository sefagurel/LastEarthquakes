# LastEarthquakes (Son Depremler)

Bu uygulama 3 farklı servisten çektiği deprem verileri ile kullanıcıya anlık olarak deplem bilgilerini ulaştırmayı amaçlamıştır. 
Uygulama open source olarak yayınlanmakta olup başlangıç aşamasındaki developerlara faydalı olacağı düşünülmektedir.

Uygulama Google Play linki : https://play.google.com/store/apps/details?id=com.sefagurel.lastearthquakes

Uygulama Android Studio ile yazılmış olup res/values/keys.xml dosyası içindeki keyler ile Manifest dosyasındaki Google Maps Key'i eklenmemiştir. Bu bilgileri kendinize göre doldurmanız gerekmektedir.

# Neler Öğreneceksiniz

* Sunucu ile iletişimin nasıl kurulacağını,
* Json datalarını nasıl objelere parse edeceğinizi,
* Android Service kullanımını,
* Google Maps haritalarını ve üzerindeki işlemleri,
* Custom Listview oluşturmayı,
* PreferenceActivity kullanarak ayarları veritabanı yerine XML dosyalarında saklamayı,
* Orm yapısını kullanarak SQL kodu yazmadan veritabanı işlemleri yapabilmeyi,
* Verileri grafiksel olarak kullanıcıya gösterebilmeyi,
* Tüm kullanıcılardan Exception ve Crash'leri toplayarak analiz edebilmeyi,
* Tüm Activityler kapalı da olsa uygulamanın arka planda çalışmaya devam edebilmesini,
* Uygulamaya nasıl reklam koyulabileceğini
 
ve daha birçok bilgiyi öğrenebileceksiniz.

# Kullanılan Kütüphaneler

 
* StartAppInApp-2.4.13.jar // reklamlar için reklam firması tarafından sağlanan kütüphane
* java-json.jar // xml formatını json formatına çevirir
* hellocharts-library-1.5.4.jar // verileri grafik arayüzüne çevirir
* crouton-1.8.5.jar // custom Toast oluşturmayı sağlar
* ormlite-core:4.23 // orm yapısı kullanarak db işlemlerinin yapılmasını sağlar
* gson:2.3.1 // json verilerini nesnelere parse edebilmenizi sağlar
* okhttp:2.3.0 // http işlemlerinin kolay bir şekilde yapılmasını sağlar
* otto:1.3.7 // olay yakalyıcı olarak çalışır
* mint:4.2 // https://mint.splunk.com üzerinden alacağınız key ile kullanıcılardan gelen exception ve crash'leri analiz edebilirsiniz.

# Not

Eğer faydalı olduğunu düşünüyorsanız yapılan çalışmalara github üzerinden ve google play üzerinden yıldız vererek destek verebilirsiniz. Destek veren tüm arkadaşlar kodları dilediğince kullanabilir. Bol Android'li günler! :) 
