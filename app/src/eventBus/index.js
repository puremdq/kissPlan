export default class eventBus {
    constructor(){
        this.eventBus = {}
    }
    on(type,fn){
        this.eventBus[type] = this.eventBus[type] || [];
        this.eventBus[type].push(fn)
    }
    off(type,fn){
        if(type){
            if(fn){
                var events = this.eventBus[type] || [];
                if(events && events.length>0){
                    events.forEach((item,idx)=>{
                        if(item===fn){
                            events.splice(idx,1)
                        }
                    })
                }
            }else{
                this.eventBus[type] = []
            }
        }else{
            this.eventBus = {} 
        }
    }
    emit(type,data){
        if(type){
            return new Promise((resolve, reject)=>{
                var events = this.eventBus[type] || [];
                if(events && events.length>0){
                    events.forEach((fn)=>{
                        fn.call(this,data);
                    })
                }
                resolve()
            })
        }
    }
}