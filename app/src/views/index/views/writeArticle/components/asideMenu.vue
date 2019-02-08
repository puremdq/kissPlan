<template>
    <div class="asideMenu">
        <el-menu default-active="1" unique-opened class="el-menu-vertical-demo">
            <el-menu-item index="1">
                <i class="el-icon-menu"></i>
                <span slot="title">导航1</span>
            </el-menu-item>
            <el-menu-item index="2">
                <i class="el-icon-menu"></i>
                <span slot="title">导航二</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>
<script>
export default {
    name:'asideMenu',
    data(){
        return {

        }
    }
}
</script>
<style lang="less">
    .asideMenu{

    }
</style>
