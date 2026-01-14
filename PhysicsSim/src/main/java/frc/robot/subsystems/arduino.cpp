#include "HX711.h"

// HX711 wiring
#define HX711_DOUT 3
#define HX711_SCK  2

HX711 scale;

// ===================
// ===== CALIBRATION =====
// CHANGE THIS VALUE after calibration
// Start with 1.0 for initial testing, then adjust
float calibrationFactor = 1.0;  // <<< CHANGE THIS AFTER CALIBRATION
// ===================

// How many samples to average (increase for small weights)
const int SAMPLE_COUNT = 30;

// How often to send data (ms)
const unsigned long UPDATE_PERIOD_MS = 200;

unsigned long lastUpdate = 0;

void setup() {
  Serial.begin(9600);           // MUST match RoboRIO or Serial Monitor
  scale.begin(HX711_DOUT, HX711_SCK);

  delay(500);                   // Let HX711 settle

  scale.set_scale(calibrationFactor);
  scale.tare();                 // Zero at startup

  Serial.println("READY");      // Optional debug
}

void loop() {
  if (millis() - lastUpdate >= UPDATE_PERIOD_MS) {
    lastUpdate = millis();

    if (scale.is_ready()) {
      // Average multiple samples to reduce noise
      float weight = scale.get_units(SAMPLE_COUNT);

      // Optional: print raw value for calibration debugging
      // Serial.print("Raw: "); Serial.print(scale.read()); Serial.print("  ");

      // CRITICAL: Send ONLY the number + newline
      Serial.println(weight);
    } else {
      Serial.println("HX711 not ready");
    }
  }
}
