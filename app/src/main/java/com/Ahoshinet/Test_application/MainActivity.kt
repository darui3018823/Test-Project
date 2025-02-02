package com.example.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerPrefecture: Spinner
    private lateinit var spinnerCity: Spinner
    private lateinit var tvSelectedCityId: TextView

    // 都道府県と市区町村IDのマップ
    private val cityIds = mapOf(
        "北海道" to mapOf(
            "稚内" to "011000",
            "旭川" to "012010",
            "留萌" to "012020",
            "網走" to "013010",
            "北見" to "013020",
            "紋別" to "013030",
            "根室" to "014010",
            "釧路" to "014020",
            "帯広" to "014030",
            "室蘭" to "015010",
            "浦河" to "015020",
            "札幌" to "016010",
            "岩見沢" to "016020",
            "倶知安" to "016030",
            "函館" to "017010",
            "江差" to "017020"
            ),
            "青森" to mapOf(
                "青森" to "020010",
                "むつ" to "020020",
                "八戸" to "020030"
            ),
            "岩手" to mapOf(
                "盛岡" to "030010",
                "宮古" to "030020",
                "大船渡" to "030030"
            ),
            "宮城県" to mapOf(
                "仙台" to "040010",
                "白石" to "040020"
            ),
            "秋田県" to mapOf(
                "秋田" to "050010",
                "横手" to "050020"
            ),
            "山形県" to mapOf(
                "山形" to "060010",
                "米沢" to "060020",
                "酒田" to "060030",
                "新庄" to "060040"
            ),
            "福島県" to mapOf(
                "福島" to "070010",
                "小名浜" to "070020",
                "若松" to "070030"
            ),
            "茨城県" to mapOf(
                "水戸" to "080010",
                "土浦" to "080020"
            ),
            "栃木県" to mapOf(
                "宇都宮" to "090010",
                "大田原" to "090020"
            ),
            "群馬県" to mapOf(
                "前橋" to "100010",
                "みなかみ" to "100020"
            ),
            "埼玉県" to mapOf(
                "さいたま" to "110010",
                "熊谷" to "110020",
                "秩父" to "110030"
            ),
            "千葉県" to mapOf(
                "千葉" to "120010",
                "銚子" to "120020",
                "館山" to "120030"
            ),
            "東京都" to mapOf(
                "東京" to "130010",
                "大島" to "130020",
                "八丈島" to "130030",
                "父島" to "130040"
            ),
            "神奈川県" to mapOf(
                "横浜" to "140010",
                "小田原" to "140020"
            ),
            "新潟県" to mapOf(
                "新潟" to "150010",
                "長岡" to "150020",
                "高田" to "150030",
                "相川" to "150040"
            ),
            "富山県" to mapOf(
                "富山" to "160010",
                "伏木" to "160020"
            ),
            "石川県" to mapOf(
                "金沢" to "170010",
                "輪島" to "170020"
            ),
            "福井県" to mapOf(
                "福井" to "180010",
                "敦賀" to "180020"
            ),
            "山梨県" to mapOf(
                "甲府" to "190010",
                "河口湖" to "190020"
            ),
            "長野県" to mapOf(
                "長野" to "200010",
                "松本" to "200020",
                "飯田" to "200030"
            ),
            "岐阜県" to mapOf(
                "岐阜" to "210010",
                "高山" to "210020"
            ),
            "静岡県" to mapOf(
                "静岡" to "220010",
                "網代" to "220020",
                "三島" to "220030",
                "浜松" to "220040"
            ),
            "愛知県" to mapOf(
                "名古屋" to "230010",
                "豊橋" to "230020"
            ),
            "三重県" to mapOf(
                "津" to "240010",
                "尾鷲" to "240020"
            ),
            "滋賀県" to mapOf(
                "大津" to "250010",
                "彦根" to "250020"
            ),
            "京都府" to mapOf(
                "京都" to "260010",
                "舞鶴" to "260020"
            ),
            "大阪府" to mapOf(
                "大阪" to "270000"
            ),
            "兵庫県" to mapOf(
                "神戸" to "280010",
                "豊岡" to "280020"
            ),
            "奈良県" to mapOf(
                "奈良" to "290010",
                "風屋" to "290020"
            ),
            "和歌山県" to mapOf(
                "和歌山" to "300010",
                "潮岬" to "300020"
            ),
            "鳥取県" to mapOf(
                "鳥取" to "310010",
                "米子" to "310020"
            ),
            "島根県" to mapOf(
                "松江" to "320010",
                "浜田" to "320020",
                "西郷" to "320030"
            ),
            "岡山県" to mapOf(
                "岡山" to "330010",
                "津山" to "330020"
            ),
            "広島県" to mapOf(
                "広島" to "340010",
                "庄原" to "340020"
            ),
            "山口県" to mapOf(
                "下関" to "350010",
                "山口" to "350020",
                "柳井" to "350030",
                "萩" to "350040"
            ),
            "徳島県" to mapOf(
                "徳島" to "360010",
                "日和佐" to "360020"
            ),
            "香川県" to mapOf(
                "高松" to "370000"
            ),
            "愛媛県" to mapOf(
                "松山" to "380010",
                "新居浜" to "380020",
                "宇和島" to "380030"
            ),
            "高知県" to mapOf(
                "高知" to "390010",
                "室戸岬" to "390020",
                "清水" to "390030"
            ),
            "福岡県" to mapOf(
                "福岡" to "400010",
                "八幡" to "400020",
                "飯塚" to "400030",
                "久留米" to "400040"
            ),
            "佐賀県" to mapOf(
                "佐賀" to "410010",
                "伊万里" to "410020"
            ),
            "長崎県" to mapOf(
                "長崎" to "420010",
                "佐世保" to "420020",
                "厳原" to "420030",
                "福江" to "420040"
            ),
            "熊本県" to mapOf(
                "熊本" to "430010",
                "阿蘇乙姫" to "430020",
                "牛深" to "430030",
                "人吉" to "430040"
            ),
            "大分県" to mapOf(
                "大分" to "440010",
                "中津" to "440020",
                "日田" to "440030",
                "佐伯" to "440040"
            ),
            "宮崎県" to mapOf(
                "宮崎" to "450010",
                "延岡" to "450020",
                "都城" to "450030",
                "高千穂" to "450040"
            ),
            "鹿児島県" to mapOf(
                "鹿児島" to "460010",
                "鹿屋" to "460020",
                "種子島" to "460030",
                "名瀬" to "460040"
            ),
            "沖縄県" to mapOf(
                "那覇" to "471010",
                "名護" to "471020",
                "久米島" to "471030",
                "南大東" to "472000",
                "宮古島" to "473000",
                "石垣島" to "474010",
                "与那国島" to "474020"
            )
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerPrefecture = findViewById(R.id.spinnerPrefecture)
        spinnerCity = findViewById(R.id.spinnerCity)
        tvSelectedCityId = findViewById(R.id.tvSelectedCityId)

        // 都道府県の選択肢を設定
        val prefectures = cityIds.keys.toList()
        val prefectureAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, prefectures)
        prefectureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPrefecture.adapter = prefectureAdapter

        // 都道府県が選ばれた時に市区町村を更新
        spinnerPrefecture.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedPrefecture = prefectures[position]
                val cities = cityIds[selectedPrefecture]?.keys?.toList() ?: emptyList()
                val cityAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, cities)
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCity.adapter = cityAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // 市区町村が選ばれた時にそのIDを取得して表示
        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCity = spinnerCity.selectedItem.toString()
                val selectedPrefecture = spinnerPrefecture.selectedItem.toString()
                val cityId = cityIds[selectedPrefecture]?.get(selectedCity)
                cityId?.let {
                    // ここで市区町村IDを表示
                    tvSelectedCityId.text = "選択された市区町村ID: $it"
                    Log.d("City ID", "City ID: $it") // Logcatに表示
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}
