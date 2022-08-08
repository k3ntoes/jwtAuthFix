#!/bin/sh
java keytool -genkey -alias caterSecret -keyalg RSA -keystore caterkey.jks -keysize 2048 -storepass SuperSecretPassword
