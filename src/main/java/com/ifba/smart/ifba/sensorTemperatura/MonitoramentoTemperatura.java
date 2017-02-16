/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifba.smart.ifba.sensorTemperatura;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.wiringpi.Gpio;
import java.math.BigInteger;

/**
 *
 * @author brenno.mello
 */
public class MonitoramentoTemperatura {
    	public static void main(String[] args) {
		Gpio.wiringPiSetup();
		try {
                    
                        final int ledR = 1;
			final int ledY = 2;
			final int ledG = 3;
			Gpio.pinMode(ledR, Gpio.OUTPUT);
			Gpio.pinMode(ledY, Gpio.OUTPUT);
			Gpio.pinMode(ledG, Gpio.OUTPUT);
			Gpio.digitalWrite(ledR, false);
			Gpio.digitalWrite(ledY, false);
			Gpio.digitalWrite(ledG, false);
                        
			final int pinoSensor = 0;
			
			Gpio.pinMode(pinoSensor, Gpio.INPUT);			

                        Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					Gpio.digitalWrite(ledR, false);
					Gpio.digitalWrite(ledY, false);
					Gpio.digitalWrite(ledG, false);
				}
			});
                        
                        
			while (true) {
				
				int valor = Gpio.analogRead(pinoSensor);
				System.err.println("Leitura: " + valor );
				
                                Gpio.digitalWrite(ledR, valor > 10);
				Gpio.digitalWrite(ledY, valor > 1000);
				Gpio.digitalWrite(ledG, valor > 10000);
				System.out.print("\r " + valor);
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}