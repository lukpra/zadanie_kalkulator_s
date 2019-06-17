<template>
  <div class="app">
    <b-card bg-variant="light">
      <b-form-group
              label-cols-lg="3"
              label="Kalkulator stawki netto"
              label-size="lg"
              label-class="font-weight-bold pt-0"
              class="mb-0"
      >
        <b-form-group
                label-cols-sm="3"
                label="Stawka dzienna"
                label-align-sm="right"
                label-for="daily-wage"
                :invalidFeedback="onDailyWageInvalid"
                :state="formValid"
        >
          <b-form-input id="daily-wage" v-model="dailyWage"></b-form-input>
        </b-form-group>
        <b-form-group
                label-cols-sm="3"
                label="Kraj"
                label-align-sm="right"
                label-for="select-country"
        >
          <b-form-select id="select-country" v-model="selectedCountry" :options="options"></b-form-select>
      </b-form-group>
        <b-button
                :disabled="!formValid"
                variant="outline-primary"
                @click="handleClick()"
        >Oblicz</b-button>
      </b-form-group>
    </b-card>
    <div class="result" v-if="!!data">
      <p>Kraj: {{ data.country }}</p>
      <p>Stawka dzienna: {{ data.dailyWage }}</p>
      <p class="netto">Kwota netto: {{ data.netto }}</p>
    </div>
    <div class="error" v-else>
      Brak danych
    </div>
  </div>
</template>
<script lang="ts" src="./calculator.component.ts" />
<style scoped lang="scss" src="./calculator.component.scss" />
