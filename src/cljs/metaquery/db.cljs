(ns metaquery.db)


(def query-template "PREFIX csv:<http://www.ntnu.no/ub/data/csv#>
  PREFIX ssn:<http://purl.oclc.org/NET/ssnx/ssn#>

  SELECT ?f ?i 
  WHERE {?f csv:hasColumn ?c .
                ?c csv:mapsTo ssn:hasValue .
                ?c csv:hasIndex ?i .}")

(def default-db
  {:name "re-frame"
   :dataset ""
   :cat-a [nil]
   :cat-b [nil]
   :filtered-cats [[]]
   :cat-as [{:id "time" :label "Time"}
            {:id "type" :label "Reading Type"}
            {:id "location" :label "Location"}
            {:id "value" :label "Value"}
            ]
   :cat-bs [{:id 0 :cat-id "time" :label "Start Time" :p "ssn:startTime"}
            {:id 1 :cat-id "time" :label "End Time" :p "ssn:endTime"}
            {:id 2 :cat-id "time" :label "Timestamp" :p "xsd:dateTimeStamp"}
            {:id 3 :cat-id "location" :label "Device" :p "dm4t:device"}
            {:id 4 :cat-id "location" :label "Sensor" :p "dm4t:sensor"}
            {:id 5 :cat-id "location" :label "Room" :p "dm4t:room"}
            {:id 6 :cat-id "location" :label "House" :p "dm4t:house"}
            {:id 7 :cat-id "location" :label "Appliance" :p "dm4t:appliance"}
            {:id 8 :cat-id "value" :label "Value" :p "ssn:hasValue"}
            {:id 9 :cat-id "value" :label "Maximum Value" :p "dm4t:maxValue"}
            {:id 10 :cat-id "value" :label "Minimum Value" :p "dm4t:minValue"}
            {:id 11 :cat-id "value" :label "Mean Value" :p "dm4t:meanValue"}
            {:id 12 :cat-id "type" :label "Power (Watts)" :p "dm4t:powerReading"}
            {:id 13 :cat-id "type" :label "Humidity (relative)" :p "dm4t:humidityReading"}
            {:id 14 :cat-id "type" :label "Gas" :p "dm4t:gasReading"}
            {:id 15 :cat-id "type" :label "CO2" :p "dm4t:co2Reading"}
            {:id 16 :cat-id "type" :label "Light" :p "dm4t:lightReading"}
            {:id 17 :cat-id "type" :label "Motion (PIR)" :p "dm4t:motionReading"}
            ]
   :sparql "PREFIX csv:<http://www.ntnu.no/ub/data/csv#>
PREFIX ssn:<http://purl.oclc.org/NET/ssnx/ssn#>

SELECT ?f ?i
WHERE {?f csv:hasColumn ?c .
       ?c csv:mapsTo ssn:hasValue .
       ?c csv:hasIndex ?i .}"
   :files []})
