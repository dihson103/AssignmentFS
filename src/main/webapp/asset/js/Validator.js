function Validator(formSelector){
    const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    const phoneRegex = /^\d{10}$/;
    const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{6,}$/;

    let formRules = {};

    let validatorRules = {
        required : (value) => value ? undefined : 'Please enter this field.',
        min : (min) => (value) => value.length >= min ? undefined : `Please enter at least ${min} ${min > 1 ? 'characters' : 'character'}`,
        max : (max) => (value) => value.length <= max ? undefined : `Please enter no more than ${max} ${max > 1 ? 'characters' : 'character'}`,
        email : (value) => value.match(emailRegex) ? undefined : 'Please input your correct email address',
        phone : (value) => value.match(phoneRegex) ? undefined : 'Please input your correct phone number',
        password : (value) => value.match(passwordRegex) ? undefined : 'The password must have at least 6 characters, including uppercase, lowercase and number'
    }

    let handleValidate = (event) => {
        let rules = formRules[event.target.name];
        let errorMessage;

        for(let rule of rules) {
            errorMessage = rule(event.target.value);
            if(errorMessage)
                break;
        }

        if(errorMessage){
            $('#' + event.target.id + ' + .form-message').html(errorMessage);
        }
        return errorMessage;
    }

    let handleClearMessageWhenInput = (event) => {
        $('#' + event.target.id + ' + .form-message').html('');
    };

    let formElement = document.querySelector(formSelector);

    if(formElement) {
        let inputs = formElement.querySelectorAll('[name][rules]')

        for(let input of inputs){
            let rulesArray = input.getAttribute('rules').split(' ');
            let rules = rulesArray.filter((rule) => rule.trim().length > 0);

            for(let rule of rules){
                let ruleFunction = validatorRules[rule];

                if(rule.includes('-')){
                    let ruleInfo = rule.split('-');
                    rule = ruleInfo[0];
                    ruleFunction = validatorRules[rule](ruleInfo[1]);
                }

                if(Array.isArray(formRules[input.name])){
                    formRules[input.name].push(ruleFunction);
                }else {
                    formRules[input.name] = [ruleFunction];
                }
            }

            //Listen event to validate
            input.onblur = handleValidate;
            input.oninput = handleClearMessageWhenInput;
        }
    }

    formElement.onsubmit = (event) => {
        event.preventDefault();
        let inputs = formElement.querySelectorAll('[name][rules]')
        let isFormValid = true;
        let objectValues = [];

        for(let input of inputs){
            if(handleValidate({target : input})){
                isFormValid = false;
            }
            objectValues[input.name] = input.value;
        }

        if(isFormValid){
            if(this.callApiFunc){
                this.callApiFunc();
            }else{
                formElement.submit();
            }
        }
    }
};