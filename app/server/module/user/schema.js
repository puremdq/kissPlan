var mongoose = require('mongoose');
var bcrypt = require('bcrypt');
var UserSchema = new mongoose.Schema({
    name:{
        unique:true,
        type:String
    },
    password:String,
    meta:{
        createAt:{
            type:Date,
            default:Date.now()
        },
        updateAt:{
            type:Date,
            default:Date.now()
        }
    }
})
UserSchema.pre('save',function(next){
    var user = this;
    if(this.isNew){
        this.meta.createAt = this.meta.updateAt = Date.now();
    }else{
        this.meta.updateAt = Date.now();
    }
    bcrypt.genSalt(10,function(err,salt){
        if(err){
            return next(err)
        }
        bcrypt.hash(user.password,salt,function(err,hash){
            if(err){
                return next(err)
            }
            user.password = hash;
            next();
        })
    })
})
UserSchema.methods = {
    comparePassword:function(_password,fn){
        bcrypt.compare(_password,this.password,function(err,isMatch){
            if(err) return fn(err)
            fn(null,isMatch)
        })
    }
}
UserSchema.statics = {
    fetch:function(cb){
        return this
            .find({})
            .sort(',eta.upDateAt')
            .exec(cb)
    }
}
module.exports  = UserSchema;