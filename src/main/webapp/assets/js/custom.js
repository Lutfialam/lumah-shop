/**
 *
 * You can write your JS code here, DO NOT touch the default style file
 * because it will make it harder for you to update.
 * 
 */

"use strict";
const input = document.querySelector('#qty');
const button = document.querySelector('#checkout_button');
const message = document.querySelector('#message');

button.addEventListener('click', (event) => {
    if (input.value.length <= 0) {
        event.preventDefault();
        message.classList.remove('d-none')
    } else {
        message.classList.add('d-none')
    }
});