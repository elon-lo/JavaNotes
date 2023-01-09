package com.yu.groovy

import groovy.xml.MarkupBuilder

def xml = new MarkupBuilder()
assert xml != null

def date = new Date().format("yyyy-MM-dd HH:mm:ss")
println(date)