Environment : IdentityDictionary {
	classvar <>stack;	
	
	*make { arg function;
		^this.new.make(function)
	}
	*use { arg function;
		^this.new.use(function)
	}

	make { arg function;
		// pushes the Envir, executes function, returns the Envir
		// usually used to create an environment by adding new variables to it.
		var result, saveEnvir;
		
		saveEnvir = currentEnvironment;
		currentEnvironment = this;
		function.value(this);
		currentEnvironment = saveEnvir;
	}
	use { arg function;
		// temporarily replaces the currentEnvironment with this, 
		// executes function, returns the result of the function
		var result, saveEnvir;
		
		saveEnvir = currentEnvironment;
		currentEnvironment = this;
		result = function.value(this);
		currentEnvironment = saveEnvir;
		^result
	}
	
	eventAt { arg key; ^this.at(key) }
	
	*pop {
		if(stack.notEmpty) { currentEnvironment = stack.pop };
	}
	*push { arg envir;
		stack = stack.add(currentEnvironment);
		currentEnvironment = envir;
	}
	pop { ^this.class.pop }
	push { this.class.push(this) }
}

